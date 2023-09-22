#REPOSITORY=/home/ubuntu/snapspot
# cd $REPOSITORY
cd ../

CURRENT_CONTAINER=$(sudo docker ps)

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill -15 $CURRENT_CONTAINER"
  sudo docker stop snapspot-server
  sudo docker stop snapspot-batch-server
  sleep 10
  sudo docker rm snapspot-server
  sudo docker rm snapspot-batch-server
  sleep 5
fi

echo "> 도커 이미지 빌드: api"
sudo docker build -t snapspot-api .

echo "> snapspot-api 도커 컨테이너 올리기"
sudo docker run -d -p 80:8080 --name snapspot-server snapspot-api

echo "> 도커 이미지 빌드: batch"
cd ./snapspot-batch
sudo docker build -t snapspot-batch .
sudo docker run -d -p 8080:8080 --name snapspot-batch-server snapspot-batch