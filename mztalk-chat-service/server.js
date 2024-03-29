const express = require('express')

const app = express()

const cors = require('cors');

const path = require('path');

const {swaggerUi, specs} = require('./swagger.js');



// app.set('view engine', 'ejs');

const http = require('http').createServer(app);
const { Server } = require("socket.io");
const io = new Server(http); 


const bodyParser = require('body-parser');

const request = require('request');

app.use(bodyParser.urlencoded({extended : true}));
app.use(cors());
app.use('/static', express.static('../mztalk-front'));

async function main() {
  const { MongoClient } = require('mongodb');
  const client = new MongoClient('mongodb://nvr1195:qsdf6452@ac-hn467ce-shard-00-00.wykughx.mongodb.net:27017,ac-hn467ce-shard-00-01.wykughx.mongodb.net:27017,ac-hn467ce-shard-00-02.wykughx.mongodb.net:27017/?ssl=true&replicaSet=atlas-31f4qm-shard-0&authSource=admin&retryWrites=true&w=majority');
  app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));

  try {
    await client.connect();
  } catch (error) {
    return console.error(error);
  }

  const db = client.db('testdata');
  const chatCollection = db.collection('chatTest');
/**
 * @swagger
 * /chat-friends?userId={userId}&userNickname={userNickname}:
 *  get:
 *    summary: "맞팔 유저와 채팅"
 *    tags: [Chat]
 *    parameters:
 *      - in: query
 *        name: userId
 *        required: true
 *        description: 유저 아이디
 *        schema:
 *          type: Integer
 *      - in: query
 *        name: userNickname
 *        required: true
 *        description: 유저 닉네임
 *        schema:
 *          type: string
 */
  const chatCollection = db.collection('chatting');

  app.get('/chat-friends', function(req, res) {
    res.sendFile(path.join(__dirname, '../', 'mztalk-front/chatService', 'chat-friends.html'));
  });
/**
 * @swagger
 * /chat-auction?userId={userId}&userNickname={userNickname}:
 *  get:
 *    summary: "입찰자와 채팅"
 *    tags: [Chat]
 *    parameters:
 *      - in: query
 *        name: userId
 *        required: true
 *        description: 유저 아이디
 *        schema:
 *          type: Integer
 *      - in: query
 *        name: userNickname
 *        required: true
 *        description: 유저 닉네임
 *        schema:
 *          type: string
 */
  app.get('/chat-auction', function(req, res) {
    res.sendFile(path.join(__dirname, '../', 'mztalk-front/chatService', 'chat-auction.html'));
  });
/**
 * @swagger
 * /chat-bung?userId={userId}&userNickname={userNickname}:
 *  get:
 *    summary: "벙 글쓴이와 채팅"
 *    tags: [Chat]
 *    parameters:
 *      - in: query
 *        name: userId
 *        required: true
 *        description: 유저 아이디
 *        schema:
 *          type: Integer
 *      - in: query
 *        name: userNickname
 *        required: true
 *        description: 유저 닉네임
 *        schema:
 *          type: string
 */
  app.get('/chat-bung', function(req, res) {
    res.sendFile(path.join(__dirname, '../', 'mztalk-front/chatService', 'chat-bung.html'));
  });

  app.get('/chat-mentoring', function(req, res) {
    res.sendFile(path.join(__dirname, '../', 'mztalk-front/chatService', 'chat-mentoring.html'));
  });

  
/**
 * @swagger
 * /conversations?targetUserId={tagetUserId}:
 *  get:
 *    summary: "상대방과의 대화 내역 가져오기"
 *    tags: [Conversation]
 *    parameters:
 *      - in: query
 *        name: targetUserId
 *        required: true
 *        description: 상대방 유저 아이디
 *        schema:
 *          type: Integer
 *      - in: header
 *        name: userId
 *        required: true
 *        description: 현재 로그인한 유저 아이디
 *        schema:
 *          type: Integer
 */
  app.get('/conversations', async (req, res) => {
    const userId = req.headers.userid;
    const targetUserId = req.query.targetUserId;

    // '나'가 상대와 나눈 모든 메시지 가져오기
    const conversations = await chatCollection
      .find({
        $or: [
          { fromUserId: userId, targetUserId }, // '나'가 상대에게 보낸 메시지 기준
          { fromUserId: targetUserId, targetUserId: userId }, // 상대가 '나'에게 보낸 메시지 기준
        ],
      })
      .toArray();

    res.json(conversations);
  });



/**
 * @swagger
 * /conversations/read?targetUserId={tagetUserId}:
 *  put:
 *    summary: "상대방과의 대화 읽음 처리"
 *    tags: [Conversation]
 *    parameters:
 *      - in: query
 *        name: targetUserId
 *        required: true
 *        description: 상대방 유저 아이디
 *        schema:
 *          type: Integer
 *      - in: header
 *        name: userId
 *        required: true
 *        description: 현재 로그인한 유저 아이디
 *        schema:
 *          type: Integer
 */
  app.put('/conversations/read', async (req, res) => {
    const userId = req.headers.userid;
    const targetUserId = req.query.targetUserId;

    // '나'가 상대와 나눈 모든 메시지 읽음 처리
    // 상대가 '나'에게 메시지를 보낸 경우에는 toUserId가 '나'의 ID고,
    // 상대의 ID는 fromUserId이기 때문에,
    // '나'가 상대와 나눈 모든 대화를 찾기 위해서는 아래의 조건으로 찾아야 함
    await chatCollection.updateMany(
      { fromUserId: targetUserId, targetUserId: userId },
      { $set: { read: true } },
    );

    res.json(true);
  });

  const port = 3000;

  http.listen(port, function() {
      console.log('listening 3000');
  });

  // 유저 ID : 소켓 ID 맵
  const userSocketMap = new Map();
  // 소켓 ID : 유저 ID 맵
  const socketUserMap = new Map();

  io.on('connection', function(socket) {
    socket.on('subscribe', ({ userId }) => {
      console.log('onSubscribe', { userId, socketId: socket.id });

      // socket.to API 사용 시 소켓 ID를 알아야 하는데,
      // 유저 ID를 기준으로 소켓 ID를 찾기 위해 각 ID를 맵핑
      userSocketMap.set(userId, socket);
      socketUserMap.set(socket.id, userId);
    });

    socket.on('sendMessage', async ({ fromUserId, targetUserId, message, sendDate }) => {
      console.log('onSendMessage', { fromUserId, targetUserId, message, sendDate });

      await chatCollection.insertOne({
        fromUserId,
        targetUserId,
        message,
        sendDate,
        read: false,
      });

      const targetSocket = userSocketMap.get(targetUserId)
      if (!targetSocket) {
        return;
      } 
      // 메시지를 받은 즉시 상대 소켓에 이벤트 발행
      socket.to(targetSocket.id).emit('receiveMessage', {
        fromUserId,
        targetUserId,
        message,
        sendDate,
      });
    });


    socket.on('disconnect', () => {
      console.log('onDisconnect', socket.id);

      const userId = socketUserMap.get(socket.id);

      if (!userId) {
        return;
      }

      userSocketMap.delete(userId);
      socketUserMap.delete(socket.id);
    });
  });
}

main();