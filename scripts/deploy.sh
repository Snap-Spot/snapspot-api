REPOSITORY=/home/ubuntu/snapspot
cd $REPOSITORY

CURRENT_CONTAINER=$(sudo docker ps)

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill -15 $CURRENT_CONTAINER"
  sudo sudo docker stop snapspot-server
  sleep 10
  sudo sudo docker rm snapspot-server
  sleep 5
fi

echo "> 도커 이미지 빌드"
sudo docker build -t snapspot-api .

echo "> 도커 컨테이너 올리기"
sudo docker run -d -p 80:8080 --name snapspot-server snapspot-api 
