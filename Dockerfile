FROM 10.253.105.75:5050/env/jdk7
     
MAINTAINER zhangjinbao <zhangjinbao@zhongan.com>

COPY ./target/train-ssm.jar /root/

WORKDIR /root/

EXPOSE 8080

CMD ["java" , "-Xmx1024m","-XX:MaxPermSize=128M","-jar", "train-ssm.jar"]