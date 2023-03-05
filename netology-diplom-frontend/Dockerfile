FROM node:14.15.1
WORKDIR /app
COPY package*.json ./
EXPOSE 8080
RUN npm install
#RUN npm audit fix
#RUN npm build
COPY . .
CMD ["npm", "run", "serve"]