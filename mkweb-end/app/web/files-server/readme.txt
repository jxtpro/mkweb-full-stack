此files-server源码来源:https://github.com/anyangdp/nginx-file-process
这里做了修改使用
copyright:
https://github.com/anyangdp/nginx-file-process

1.maven 打包（会生成两个包，有boot的那个支持jar方式运行部署）
2.nohup java -jar demo.jar > demp.txt 2>&1 & (jar方式部署命令，容器部署自行解决)

fix
sun.misc.BASE64Decoder是内部专用 API, 可能会在未来发行版中删除maven-compiler-plugin