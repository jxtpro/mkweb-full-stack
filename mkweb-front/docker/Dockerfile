ARG OS_VER=latest
FROM centos:$OS_VER


#基础环境配置
RUN yum install java-1.8.0-openjdk nginx -y \
    && yum clean all && rm -rf /var/cache/yum/*

#服务器基础设置
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' > /etc/timezonesource 

EXPOSE 80 8803

ADD *.jar /home/mkweb/app.jar
ENTRYPOINT  ["java","-Dspring.profiles.active=prod", "-jar", "/home/mkweb/app.jar"]
CMD ["/usr/sbin/nginx"]