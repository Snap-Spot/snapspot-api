REPOSITORY=/home/ubuntu/snapspot
cd $REPOSITORY

CURRENT_CONTAINER=$(sudo docker ps)

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill -15 $CURRENT_CONTAINER"
  sudo docker-compose down
  sudo docker rm -f $(sudo docker ps -aq)
  sudo docker rmi $(sudo docker images -q)
fi

echo "> 도커 이미지 빌드: api"
sudo docker-compose up -d

echo "> 도커 이미지 빌드: batch"
cd ./snapspot-batch
sudo docker build -t snapspot-batch .
sudo docker run -d -p 8080:8080 --name snapspot-batch-server snapspot-batch
