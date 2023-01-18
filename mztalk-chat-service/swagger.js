const swaggerUi = require("swagger-ui-express");
const swaggerJsdoc = require('swagger-jsdoc');

const options = {
    swaggerDefinition: {
      openapi: "3.0.0",
      info: {
        version: "1.0.0",
        title: "mztalk-chat-service",
        description:
          "mztalk-chat-service-apis",
      },
      servers: [
        {
          url: "http://localhost:3000", 
        },
      ],
    },
    apis: ["./server.js"]
  }
  const specs = swaggerJsdoc(options)
  
  module.exports = { swaggerUi, specs }