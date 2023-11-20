REPOSITORY=/home/ubuntu/app
cd $REPOSITORY

CURRENT_CONTAINER=$(sudo docker ps -aq --filter "name=snapspot")

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill  $CURRENT_CONTAINER"
  sudo docker rm -f $CURRENT_CONTAINER
  sudo docker rmi $CURRENT_CONTAINER
fi

sudo docker build -t snapspot .
sudo docker run -d -p 80:8080 --name snapspot --network app snapspot
