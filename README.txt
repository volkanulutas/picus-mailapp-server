# picus-mailapp-server
Volkan Ulutaş

Java Spring Boot Uygulaması olarak geliştirilmiştir.

- Uygulama H2 inmap veritabanı kullanmaktadır. Uygulama başladığında sample veriler oluşturulmaktadır.
Örneğin: volkanulutaspicus@gmail.com kullanıcı yaratılmaktadır. 
Sunucu uygulama kapatıldığında kaydetmiş olduğunuz veriler, inmap veritabanı olduğu için silinecektir.
(Veritabanı kurulumlarının önüne geçilmek için böyle bir yol izlenmiştir.)

# Kurulum 

## Ön Yüklemeler 
- Java 8 JDK kurulumunu bilgisayarınıza yapınız. 
- http://localhost:8080 adresinde çalışan sunucu uygulama için 8080 portunda herhangi bir uygulama çalışmadığından emin olunuz.

## Çalıştırma Adımları

- mailapp_latest_executable.rar dosyayı bilgisayarınıza indiriniz.
- mailapp_latest_executable.rar klasörünü, bilgisayarınızda uygun bir dizine çıkartınız. 
(Bundan sonra </SERVER_DIZIN> olarak bahsedilecektir.)
- Daha sonra çıkartmış olduğunuz dizine gidiniz </SERVER_DIZIN>. Bu dizinde terminal açıp aşağıdaki komutu çalıştırınız:
java -jar mail-app-0.0.1-SNAPSHOT.jar
komutunu çalıştırınız. Böylece server uygulaması başlatılmış olur. 

- http://localhost:8080 adresinde uygulama çalışmaya başlar.

## Admin E-Posta Adresi Bilgileri
Kullanıcı Adı: volkanulutaspicus@gmail.com
Şifre: pic9000*vEm

- Bu bilgilere picus-mailapp-server\mailapp\src\main\resources\application.properties adresinden 
"spring.mail.username" ve "spring.mail.password" alanlarından erişilebilir.

- Bu adres üzerinden mail gönderimi sağlanır. 

## Notlar
- Sunucu uygulamada javadoc'lar ve unit tesler zaman kısıtından dolayı eksik kalmıştır, geriye kalan her şey (bonuslar dahil) tamamlanmıştır.

Herhangi bir sorununuzda volkanulutas@gmail.com adresi üzerinden iletişim kurabilirsiniz.

