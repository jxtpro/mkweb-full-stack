FROM centos:latest

#基础环境配置
RUN yum install java-1.8.0-openjdk nginx -y \
    && yum install epel-release -y \
    && yum update -y \
    && yum install supervisor -y \
    && yum clean all && rm -rf /var/cache/yum/*

ADD *.jar /home/mkweb/app.jar

# 创建目录
RUN mkdir -p /home/files-server/static_files

#服务器基础设置
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' > /etc/timezonesource \
    &&echo [supervisord] > /etc/supervisord.conf \
    && echo nodaemon=true >> /etc/supervisord.conf \
    && echo user=root >> /etc/supervisord.conf \
    && echo [program:nginx] >> /etc/supervisord.conf \
    && echo command=/usr/sbin/nginx >> /etc/supervisord.conf \
    && echo [program:java] >> /etc/supervisord.conf \
    && echo command=java -Dspring.profiles.active=prod -jar /home/mkweb/app.jar >> /etc/supervisord.conf

EXPOSE 80 8803

CMD ["/usr/bin/supervisord"]