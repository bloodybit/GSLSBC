# Installation manual

On a Debian-based system, execute the following commands to build GReg

- Install maven and git:
```
sudo apt-get update && sudo apt-get dist-upgrade
sudo apt-get -y install maven git
```

- Install Docker (https://docs.docker.com/engine/installation/linux/debian/):
```
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://apt.dockerproject.org/gpg | sudo apt-key add -
sudo add-apt-repository "deb https://apt.dockerproject.org/repo/ debian-$(lsb_release -cs) main"
sudo apt-get -y install docker-engine
```

- Compile GSLS:
```
cd /var
sudo mkdir gslsbc
cd gslsbc
sudo git clone https://github.com/reTHINK-project/dev-registry-global.git
sudo git checkout tags/0.0.1
sudo mvn clean
sudo mvn install
docker build -t rethink/gslsbc:0.0.1 .
```

- Run GSLS:
```
docker run -d -p 2001:2001/tcp -p 2001:2001/udp -p 2002:2002/tcp rethink/gslsbc:0.0.2
```
