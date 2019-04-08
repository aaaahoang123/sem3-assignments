# Web component assignment

- Add thêm thư viện:
   + javax.servlet:javax.servlet-api:3.1.0
   + mysql:mysql-connector-java:8.0.15
   
- Nếu gặp vấn đề với static resource, hãy:
   - Mở file web/WEB-INF/layouts/master 
   - Tìm dòng ``<base href="/wc_assignment_war_exploded/">``
   - Sửa href thành root resource của project