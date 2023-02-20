FROM node:14
WORKDIR /app
COPY package*.json ./
EXPOSE 8080
RUN npm install
COPY . .
CMD [ "npm", "run", "serve" ]