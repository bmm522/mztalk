const express = require('express')

const app = express()

const cors = require('cors');

const http = require('http');

const io = require('socket.io')(http);
const server = http.createServer(app);

const {Server} = require("socket.io");

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({extended : true}));

app.use(cors());

const MongoClient = require('mongodb').MongoClient


var db;
MongoClient.connect('mongodb://nvr1195:qsdf6452@ac-hn467ce-shard-00-00.wykughx.mongodb.net:27017,ac-hn467ce-shard-00-01.wykughx.mongodb.net:27017,ac-hn467ce-shard-00-02.wykughx.mongodb.net:27017/?ssl=true&replicaSet=atlas-31f4qm-shard-0&authSource=admin&retryWrites=true&w=majority',
                     function(err, client){
    if(err) return console.log(err);

    db = client.db('testdata');

     db.collection('post').insertOne({이름 : 'ㅁㄴㅇㄹ', 나이 : 20}, function(err, res) {
         console.log('저장완료');
     })

    
    })
    app.listen(7999, function() {
        console.log('listening 8000');
    });

        app.get('/chat/{id}', function(req, res) {
          
     });
  
     



















  // const request = require('request');

  // const friendList = [];














  // request.get("http://localhost:8000/login/user-info/{id}", function (err, res, body) {
  //   if(err) return console.log(err);

  //   io.on('connection', function(socket) {
  //     const friendId = body.bodyParser(body.friendId);
  //     const friendName = body.bodyParser(body.nickName);
      
  //     const user = body.bodyParser(res);
  //     io.to(socket.id).emit('nickName' , friendName);

  //     io.to(socket.io).emit('friendId' , friendId);
      
  //   });



    
  // });


  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // const eventSources = new EventSource(stream_url);

  // const notification = [user.notification]
  // eventSources.addEventListener('open', function(e) {
  //   console.log('sse 연결');
  // })

  // eventSources.addEventListener('message', function(e) {
  //  if(io.connect) {
  //   io.on("connection", (socket, err)=> {

  //       if(err) return res.send(err);
        
  //       socket.join(friendId);

  //       io.to(friendId).emit(socket ,(msg)=>{
            
  //       })
  //   });
  //  }
  // })
    
    














// app.get('/chat', function(req, res){
//     console.log('get요청 수신');
// })

// app.get('/', function(req, res) {
//     r













//                           test