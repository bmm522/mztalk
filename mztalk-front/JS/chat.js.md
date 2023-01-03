<!-- Ctrl + Shift + V -->

# 채팅 기능

## API 서버

### 애플리케이션 진입

`mztalk-chat-service2\server.js:25`
`mztalk-chat-service2\server.js:130`

```js
async function main() {
    ...
}

main();
```

`server.js` 파일을 Node.js로 실행하면 즉시 `main()` 함수를 호출하여 서버 애플리케이션을 시작합니다. 이 애플리케이션은 Express.js로 구현된 API와 채팅 중계를 위한 웹 소켓 서버를 서빙합니다.

### 정적 파일 서빙

`mztalk-chat-service2\server.js:23`

```js
app.use('/static', express.static('../mztalk-front'));
```

`/static/*` 경로를 정적 파일 서빙을 위한 경로로 설정합니다. 이 경우에는 `mztalk-front\chat.html`에서 불러오는 CSS/JS 파일을 서버에 요청하여 받아올 수 있도록 `mztalk-front`의 모든 파일을 웹 서버에서 서빙합니다.

`mztalk-chat-service2\server.js:38-40`

```js
  app.get('/chat', function(req, res) {
    res.sendFile(path.join(__dirname, '../', 'mztalk-front', 'chat.html'));
  });
```

이 문단에서 서빙하는 정적 파일과는 별개로, `/chat` 경로로 접속 시 `mztalk-front\chat.html` 파일을 서빙하기 위해 특정한 경로를 지정해 주었습니다.

### 채팅 관련 API

`mztalk-chat-service2\server.js:42-56`

```js
  app.get('/conversations', async (req, res) => {
    ...
  });
```

MongoDB에 저장된 대화 내역을 가져오는 API입니다. 채팅 UI 진입 시 읽지 않은 메시지 표기, 이전 대화 내역 로드 등에 필요한 API입니다.

`mztalk-chat-service2\server.js:58-68`

```js
  app.put('/conversations/read', async (req, res) => {
    ...
  });
```

MongoDB에 저장되어 있는, 특정 상대가 보냈던 모든 대화를 읽음 상태로 업데이트 하는 API입니다. 이 API가 실행되어 각 document의 `read` 값이 `true`가 되면 채팅 UI 로드 시 이미 읽음으로 표기합니다.

## 웹 소켓 서버

`mztalk-chat-service2\server.js:14`

```js
const io = new Server(http); 
```

Socket.io 객체를 웹 서버에 할당합니다. 웹 서버가 특정 포트(이 경우에는 3000번)로 리스닝을 하게 되면 웹 소켓 서버도 같이 실행 됩니다. 이 때부터 웹 소켓 클라이언트가 웹 소켓 연결을 수립할 수 있습니다.

### `connection` 이벤트

`mztalk-chat-service2\server.js:79-125`

```js
  io.on('connection', function(socket) {
    ...
  });
```

웹 소켓 연결이 수립 되었을 때 실행되는 `connection` 이벤트의 핸들러입니다. 이 때 `socket` 객체가 생성 되고, 이 객체를 통해 클라이언트와의 지속적인 연결을 유지합니다.

이후 모든 이벤트 핸들러는 Socket 객체가 생성되는 이 이벤트 핸들러 안에서 정의합니다.

### `subscribe` 이벤트

`mztalk-chat-service2\server.js:80-85`

```js
    socket.on('subscribe', ({ userId }) => {
      console.log('onSubscribe', { userId, socketId: socket.id });

      userSocketMap.set(userId, socket);
      socketUserMap.set(socket.id, userId);
    });
```

특정 클라이언트가 웹 소켓 연결 수립 시, 해당 클라이언트가 가지고 있는 유저 ID와 소켓 객체의 ID를 맵핑하기 위해 발생하는 `subscribe` 이벤트의 핸들러입니다.

1:1 채팅 구현을 위해서는 소켓 A가 소켓 B에만 이벤트를 보낼 수 있어야 하는데, 클라이언트 A에서 상대 B 클라이언트의 소켓 ID를 알 수 없으니, 두 클라이언트가 웹 소켓 서버와 연결을 수립한 뒤, 자신의 유저 ID를 알려 주어 사전에 유저 ID와 소켓 ID를 맵핑하는 과정을 처리합니다.

추후 특정 유저의 연결 상태를 파악하는 기능 구현을 위해 소켓 ID를 기준으로 유저 ID를 알 수 있도록 유저 ID -> 소켓 ID, 소켓 ID -> 유저 ID 양방향으로 맵핑했습니다.

### `sendMessage` 이벤트

`mztalk-chat-service2\server.js:87-110`

```js
    socket.on('sendMessage', async ({ fromUserId, targetUserId, message, sendDate }) => {
        ...
    });
```

클라이언트가 메시지를 보낼 때 발행하는 이벤트입니다. `targetUserId`를 기준으로 [`subscribe` 이벤트](###-subscribe-이벤트) 문단에서 맵핑한 소켓 ID를 찾아 해당 소켓에만 이벤트를 발행하여 1:1 메시지가 오갈 수 있도록 합니다.

상대가 접속중이지 않다면 맵핑된 소켓 객체가 없을 수도 있기 때문에, 이 이벤트가 발행되면 가장 먼저 MongoDB에 메시지 원본을 저장한 뒤, 상대의 소켓 객체를 찾아 있는 경우에만 이벤트를 발행합니다.

#### `receiveMessage` 이벤트

`mztalk-chat-service2\server.js:104-109`

```js
    socket.to(targetSocket.id).emit('receiveMessage', {
        fromUserId,
        targetUserId,
        message,
        sendDate,
    });
```

이 이벤트는 클라이언트에서 구독(`.on()`)하는 이벤트입니다. `sendMessage` 이벤트가 발행된 경우 해당 메시지 객체를 인자로 하여 `receiveMessage` 이벤트를 발행하면, 해당 이벤트를 구독한 클라이언트가 메시지 객체를 받게 됩니다.

### `disconnect` 이벤트

`mztalk-chat-service2\server.js:113-125`

```js
    socket.on('disconnect', () => {
      console.log('onDisconnect', socket.id);

      const userId = socketUserMap.get(socket.id);

      if (!userId) {
        return;
      }

      userSocketMap.delete(userId);
      socketUserMap.delete(socket.id);
    });
```

소켓 연결이 끊어져 [`connection` 이벤트](###-connection-이벤트)에서 생성된 소켓 객체가 사라질 때 발행되는 이벤트입니다. 이 이벤트가 발행되면 서버는 유저 ID와 소켓 ID 간의 맵핑을 해제합니다.

## 채팅 UI(클라이언트)

### 애플리케이션 진입

`mztalk-front\chat.html:23`

```html
    <script src="static/JS/chat.js"></script>
```

현재 창에서 열려 있는 호스트(로컬 기준으로 `http://localhost:3000`)를 기준으로 `static/JS/chat.js` 파일을 스크립트로 로드합니다.

`mztalk-front\JS\chat.js:3-6`

```js
window.addEventListener('load', main);

// 최초 진입
async function main() {
    ...
}
```

`window` 객체의 `load` 이벤트가 발생하면 `main()` 함수를 실행합니다. `main()` 함수에서는 DOM을 조작하는 로직을 실행하기 때문에, DOM이 모두 로드되지 않은 `load` 이벤트 발생 전에 실행하게 되면 DOM 조작에 오류가 발생하여 애플리케이션이 실행 되지 않습니다.

`main()` 함수는 [Controller](###-Controller) 문단에서 정의하는 각 컴포넌트에서 발생하는 이벤트 핸들러를 등록하고 관리합니다.

### Repository

`mztalk-front\JS\chat.js:299-362`

```js
class FollowListRepository {
    ...
}

class UserRepository {
    ...
}

class ConversationRepository {
    ...
}
```

채팅 기능에 필요한 데이터를 API로부터 가져오는 기능을 하는 클래스들입니다.

| Class Name             | Description                                                                                       |
|------------------------|---------------------------------------------------------------------------------------------------|
| FollowListRepository   | 현재 접속한 유저 '나'와 맞팔 상태인 모든 유저를 가져오는 API를 호출합 니다.                           |
| UserRepository         | `FollowListRepository`에서 가져온 유저 정보를 토대로 각 유저의 상세 정보를 가져오는 API를 호출합니다. |
| ConversationRepository | '나'와 특정 유저가 나눈 모든 대화 내역을 가져오는 API를 호출합니다. 특정 유저와 나눈 대화를 모두 읽음 처리하는 API를 호출합니다. |

### Controller

`mztalk-front\JS\chat.js:89-297`

```js
class MessageBoxController {
    ...
}

class ChatBoxController {
    ...
}
```

채팅 UI를 크게 두 부분(Component)으로 나누어, 각 컴포넌트가 담당하는 기능을 구현하고 DOM을 관리합니다.

| Class Name           | Description                       |
|----------------------|-----------------------------------|
| MessageBoxController | 좌측 유저 리스트 영역을 관리합니다. 유저 선택 이벤트 발생, 읽지 않은 메시지 표기 등의 기능을 구현합니다. |
| ChatBoxController    | 우측 채팅창 영역을 관리합니다. `MessageBoxController`에서 선택된 유저와 나눈 대화 표시, 메시지 발송 등의 기능을 구현합니다. |

