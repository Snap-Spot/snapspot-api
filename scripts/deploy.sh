REPOSITORY=/home/ubuntu/snapspot
cd $REPOSITORY

kubectl delete pod snapspot-api

sudo docker build -t snapspot-api .

kubectl apply -f snapspot-pod.yaml

kubectl apply -f snapspot-service.yaml
