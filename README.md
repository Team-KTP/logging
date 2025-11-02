## 어떤 걸 로그로 남겨야 할까?
- 트러블 슈팅에 도움될 것 같은 로그를 남긴다.(법적으로 필요한것이라면 당연히 남기기)
1. 요청/응답 로그
2. 오류 및 예외 로그
3. 사용자 활동 로그
4. 시스템 상태 로그(잘 안함. 모니터링 구축해서 함)
5. 데이터베이스 쿼리 로그
6. 보안 로그
7. 배치 작업 로그
8. 디버깅 로그

상황에 따라 조합하여 활용하자.

## 예외와 로그
#### Checked Exception과 UnChecked Exception은 무슨 차이인가
- Checked Exception은 컴파일시점에 예외에 대한 처리를 강제하고,
- UnChecked Exception은 예외에 대한 처리를 강제하지 않는다.


## 로그 레벨

- `TRACE` : 가장 세부적인 수준의 로그로, 코드의 세부적인 실행 경로를 추적할 때 사용
- `DEBUG` : 디버깅 목적의 로그로, 개발 중 코드의 상태나 흐름을 이해하기 위해 사용
- `INFO` : 시스템의 정상적인 운영 상태를 나타내는 정보성 로그로, 중요한 이벤트나 상태 변화를 기록 가능하면 비지니스적으로 의미 있는 것 남기기
- `WARN` : 잠재적으로 문제가 될 수 있는 상황을 나타내지만, 시스템 운영에는 즉각적인 영향을 주지 않는 경우 사용
- `ERROR` : 치명적이지 않지만, 중요한 문제가 발생했음을 나타낸다. 복구가 필요하거나 실패한 작업을 추적해야 할 때 사용
- `FATAL` : 시스템 운영을 계속할 수 없을 정도로 심각한 오류가 발생했을 때 사용

어플리케이션에서 예외 발생 시 무조건적으로 `ERROR` 레벨로 로그를 남기는 것은 아니다.

## 로그 고도화
- 스프링 애플리케이션에서 여러 API 요청/응답에 대한 로그 기록을 중앙화하고 코드 중복을 줄이기 위해 제안된 방식은 무엇인가요?
    - 로깅 필터를 구현하면 애플리케이션으로 들어오고 나가는 모든 요청/응답에 대해 공통 로직(로깅 포함)을 적용할 수 있어 코드 중복을 줄이고 중앙화된 로깅이 가능합니다.

- 요청 URL과 요청 바디를 한번에 로깅하려면 Spring Filter를 활용하는 것이 적합하다.
- yml을 통해 로그 출력 레벨을 설정할 수 있다. 원하는 패키지 범위 단위로도 가능
- 문제 발생 시 원인 파악을 위해 사용자의 요청이 무엇이었고 애플리케이션이 어떻게 응답했는지 기록하는 것이 중요합니다.


## Logback 설정

#### Logback이란
- 로깅 프레임워크이다.
- SLF4J는 로깅 프레임워크의 추상화 계층으로,
- Logback 외 다른 프레임워크로 변경 시 코드 수정 없이 설정만 바꾸면 되도록 유연성을 제공.
- 로그 백 같은 로깅 프레임워크는 Slf4j의 구현체가 되는것이다.
- 실무에선 로그를 남길떄 gzip으로 압축을 많이 시킴
``` xml
<configuration>
    <property name="LOG_FILE" value="application.log"/>

    <!-- 콘솔 출력 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 파일 출력 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_FILE}</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>application.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Logger 설정 -->
    <root level="info">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```



