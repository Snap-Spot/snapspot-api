cd /snapspot

kubectl delete pod snapspot-api

sudo docker build -t snapspot-api .

kubectl apply -f snapspot-pod.yaml
