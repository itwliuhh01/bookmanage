//
//package com.limai.test;
//
//import com.limai.App;
//import com.limai.entity.Book;
//import com.limai.service.EmployeeService;
//import com.limai.service.RedisService;
//import io.swagger.annotations.ApiModelProperty;
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = App.class)
//public class ApplicationTest {
//
//    private static final Logger LOG = Logger.getLogger(ApplicationTest.class);
//
//
//    @Autowired
//    private RedisService redisService;
//
//
//    @Test
//    public void testRedisService(){
//        Book book = new Book();
//        book.setAuthor("刘海华");
//        book.setBookName("刘海华first book");
//        redisService.set("book",book);
//    }
//
//    @Autowired
//    private EmployeeService employeeService;
//    @Test
//    public void testRemoteCall(){
//        employeeService.queryALLDepts("ding988d9745cfa4437635c2f4657eb6378f","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIwNDExNDE0NTEzMjEwODY0MTUiLCJpYXQiOjE1NDE3NjUyNzN9.YmYsBKlZd7Tlpp558eZKVqdu_uZRS72F0S9mucwVaqw");
//    }
//}
