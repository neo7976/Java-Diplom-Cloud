FROM node:14.15.1
WORKDIR /usr/src/backend
COPY package*.json ./
EXPOSE 8080
RUN npm install
COPY . .
CMD ["npm", "run", "serve"]