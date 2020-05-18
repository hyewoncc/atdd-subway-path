# 지하철 경로 조회 - TDD

## 기능 목록

- HTTP 캐시를 적용해야 한다
    - 지하철 노선도 조회시 Etag를 사용한다
    - LineControllerTest의 Etag 테스트를 성공시킨다
    
- 경로 조회 API
    - 출발역과 도착역을 입력받는다
    - 최단 거리 기준으로 경로와 기타 정보를 응답한다
        - 총 소요시간, 총 거리 등
    - 최단 경로가 하나가 아닐 경우 랜덤하게 응답
    - 최소 시간 기준으로도 경로와 기타 정보를 응답한다
    

- 예외 사항을 처리한다
    - 단위 테스트를 통해 Side 케이스를 검증한다
        - 출발역과 도착역이 같은 경우
        - 출발역과 도착역이 연결되어 있지 않은 경우
        - 존재하지 않는 출발역이나 도착역을 조회 할 경우
