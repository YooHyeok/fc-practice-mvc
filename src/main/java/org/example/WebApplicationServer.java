package org.example;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    /**
     *
     * 톰캣을 통해 해당 클래스를 빌드하고 실행할 때 webapps\WEB-INF\classes\ 하위에 빌드된 ApplicationServer 클래스파일을 로드한다.
     * 톰캣 application developer's guide
     * https://tomcat.apache.org/tomcat-9.0-doc/appdev/deployment.html
     * Standard Directory Layout - /WEB-INF/classes 하위에 자바클래스파일을 넣어라 라고 안내되어있다.
     *
     * 0) src와 동일 레벨에 webapps 폴더 생성 (빌드 설정을 생략할 경우 서버구동시 해당 경로를 찾을 수 없기 때문에 임시 생성)
     *
     * [빌드 디렉토리 설정]
     * 1) Project Structure Ctrl + Shift + Alt + s
     * 2) Modules
     * 3) 프로젝트 - main 선택
     * 4) Paths - Compile Output -Output Path
     * 5) \out\production\을 \webapps\WEB-INF\로 변경
     * 6) main말고 test도 동일하게 진행
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapps/"; // Root Directory
        Tomcat tomcat = new Tomcat(); // Tomcat객체 생성
        tomcat.setPort(8080); // 포트 8080 설정

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath()); // localhost:8080/ 로 접속시 Root Directory인 webapps 하위에서 빌드된 클래스파일(들) 탐색
        log.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}