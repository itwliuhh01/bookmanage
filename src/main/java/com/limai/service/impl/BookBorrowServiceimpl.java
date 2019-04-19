package com.limai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.limai.dao.BookMapper;
import com.limai.dao.BookUserMapper;
import com.limai.entity.Book;
import com.limai.entity.BookType;
import com.limai.entity.BookUser;
import com.limai.entity.User;
import com.limai.enumObj.ResMsgEnum;
import com.limai.myException.BorrowBookException;
import com.limai.myException.ReturnBookException;
import com.limai.requestVo.BorrowBookRequestVo;
import com.limai.requestVo.QueryUserBorrowBookRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookResponseVo;
import com.limai.responseVo.QueryUserBorrowBookResponseVo;
import com.limai.service.BookBorrowService;
import com.limai.service.EmployeeService;
import com.limai.service.RedisService;
import com.limai.service.UserService;
import com.limai.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName BookBorrowServiceimpl
 * @Description 客户借书
 * @Author LiuHaihua
 * @Date 2018/11/4 16:08
 * @Version 1.0
 **/
@Service
public class BookBorrowServiceimpl implements BookBorrowService{
    private Logger logger = LoggerFactory.getLogger(BookBorrowServiceimpl.class);
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookUserMapper bookUserMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public BaseResponse borrowBook(String token,BorrowBookRequestVo borrowBookRequestVo) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            logger.info("borrowBook方法入参:{}", JSONObject.toJSONString(borrowBookRequestVo));
            Book book = new Book();
            book.setIsbn(borrowBookRequestVo.getIsbn());
            //将图书状态修改成已经借阅，不能借阅0
            book.setBookStatus("0");
//            修改指定图书的借阅状态
            int updateBookStatusRes = bookMapper.updateBookStatus(book);

            //向bookuser表中插入一条记录
            BookUser bookUser = new BookUser();
            bookUser.setBookIsbn(borrowBookRequestVo.getIsbn());
//            图书借阅表中改成已经借阅
            bookUser.setBorrowStatus("1");
            bookUser.setReturnDate(TimeUtils.getAfterMonth(1));
            bookUser.setBorrowDate(TimeUtils.getDateTime_I(new Date()));

            String userId = getUserId(token,borrowBookRequestVo.getUserId());
            //查询不到用户抛出异常
            if(StringUtils.isEmpty(userId)) throw new BorrowBookException(ResMsgEnum.C00006);
            bookUser.setUserId(userId);
            String id = UUID.randomUUID().toString().replaceAll("-","");
            bookUser.setId(id);
            int insertRes = bookUserMapper.insert(bookUser);
            //同时更新成功才算成功，否则抛出异常回滚事物
            if(insertRes>0 && updateBookStatusRes>0){
                baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
                baseResponse.setData(true);
            }else{
                throw new BorrowBookException(ResMsgEnum.E00001);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("borrowBook方法发生异常:{}", e.getMessage());
            throw new BorrowBookException(ResMsgEnum.E00001);
        }
        logger.info("borrowBook方法出参:{}", JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }
    /**
     * @method returnBook
     * @author LiuHaihua
     * @Description 还书
     * @Date 8:02 2018/11/5
     * @Param borrowBookRequestVo
     * @return BaseResponse
     **/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse returnBook(BorrowBookRequestVo borrowBookRequestVo) {
        BaseResponse baseResponse = new BaseResponse();
        logger.info("returnBook方法入参:{}",JSONObject.toJSONString(borrowBookRequestVo));
        try{
            String isbn = borrowBookRequestVo.getIsbn();
            String userId = borrowBookRequestVo.getUserId();
            Book book = new Book();
            book.setIsbn(isbn);
            book.setBookStatus("1");
            int updateBookRes = bookMapper.updateBookStatus(book);

            BookUser bookUser = new BookUser();
            bookUser.setBorrowStatus("0");
            bookUser.setReturnDate(TimeUtils.getDateTime_I(new Date()));
            //根据bookuser中唯一主键id更新表，主要是为了防止用户多次借阅同一本书
            //造成更新数据错乱
            bookUser.setId(borrowBookRequestVo.getBookUserid());
            int updateBookUserRes = bookUserMapper.updateBookUser(bookUser);
            //同时更新成功才算成功，否则抛出异常回滚事物
            if(updateBookRes>0 && updateBookUserRes>0){
                baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
                baseResponse.setData(true);
            }else{
                throw new ReturnBookException(ResMsgEnum.E00002);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("还书returnBook方法发生异常:{}",e.getMessage());
            throw new ReturnBookException(ResMsgEnum.E00002);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse renewBook(BorrowBookRequestVo borrowBookRequestVo) {
        logger.info("renewBook方法入参:{}",JSONObject.toJSONString(borrowBookRequestVo));
        BaseResponse baseResponse = new BaseResponse();
        try{
            BookUser bookUser = new BookUser();
            bookUser.setReturnDate(TimeUtils.getAfterMonth(1));
            bookUser.setBorrowStatus("2");
            bookUser.setUserId(borrowBookRequestVo.getUserId());
            bookUser.setBookIsbn(borrowBookRequestVo.getIsbn());
            bookUser.setId(borrowBookRequestVo.getBookUserid());
            bookUserMapper.updateBookUser(bookUser);
            baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
            baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
            baseResponse.setData(true);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        logger.info("renewBook方法出参:{}",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }

    @Override
    public BaseResponse queryUserBorrowedBooks(String token,QueryUserBorrowBookRequestVo queryUserBorrBookRequestVo) {
        logger.info("queryUserBorrowedBooks方法入参:{}",JSONObject.toJSONString(queryUserBorrBookRequestVo));
        BaseResponse baseResponse = new BaseResponse();
        try{
            String userId = getUserId(token,queryUserBorrBookRequestVo.getUserId());
            String mobile = userId;
            queryUserBorrBookRequestVo.setUserId(userId);
            PageHelper.startPage(queryUserBorrBookRequestVo.getPageNum(),queryUserBorrBookRequestVo.getPageSize());
            List<BookUser> bookUserList = bookUserMapper.queryUserBorrowBooks(queryUserBorrBookRequestVo);
            List<QueryUserBorrowBookResponseVo> queryUserBorrowBookResponseVos = new ArrayList<QueryUserBorrowBookResponseVo>();
            for (BookUser bookUser : bookUserList){
                QueryUserBorrowBookResponseVo borrowBookResponseVo = new QueryUserBorrowBookResponseVo();
                Book book = bookUser.getBook();
                BookType bookType = book.getBookType();
                BeanUtils.copyProperties(bookUser,borrowBookResponseVo);
                BeanUtils.copyProperties(book,borrowBookResponseVo);
                BeanUtils.copyProperties(bookType,borrowBookResponseVo);
                borrowBookResponseVo.setBookUserId(bookUser.getId());
                borrowBookResponseVo.setBorrowDate(TimeUtils.formatDateStr(borrowBookResponseVo.getBorrowDate()));
                borrowBookResponseVo.setReturnDate(TimeUtils.formatDateStr(borrowBookResponseVo.getReturnDate()));
                borrowBookResponseVo.setPublicationdate(TimeUtils.formatDateStr(borrowBookResponseVo.getPublicationdate()));
                queryUserBorrowBookResponseVos.add(borrowBookResponseVo);
            }
            PageInfo pageInfo = new PageInfo(queryUserBorrowBookResponseVos);
            baseResponse.setData(pageInfo);
            baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
            baseResponse.setMessage(ResMsgEnum.C00001.getMessage());

        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setStatus(ResMsgEnum.C00002.getStatus());
            baseResponse.setMessage(ResMsgEnum.C00002.getMessage());
            new RuntimeException(e.getMessage());
        }
        logger.info("queryUserBorrowedBooks方法出参:{}",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }

    /**
     * @method getUserId
     * @author LiuHaihua
     * @Description 查询用户信息
     * @Date 18:40 2018/11/10
     * @Param 
     * @return 
     **/
    public String getUserId(String token,String mobile){
        String userId = "";
        String mob = "";
        //step1、查询redis中是否缓存用户
        User userRedis = (User)redisService.getObject("bookmanage:authentication:"+token);
        User user = null;
        if(userRedis != null){
            mob = userRedis.getMobile();
            if(mobile.equals(mob)){
                userId = userRedis.getUserId();
            }else{
                //step2、查询数据库中是否有该用户
                user = userService.selectUserByMobile(mobile);
                if (user != null && !StringUtils.isEmpty(user.getUserId())){
                    userId = user.getUserId();
                }else{
                    //step3、查询钉钉接口数据
                    BaseResponse baseResponse = employeeService.queryAlLEmployee(userRedis.getCorpId(),userRedis.getDingToken());
                    String status = baseResponse.getStatus();
                    if ("1".equals(status)){
                        JSONArray data = (JSONArray)baseResponse.getData();
                        for(int i=0;i<data.size();i++){
                            JSONObject obj1 = (JSONObject) data.get(i);
                            mob = obj1.getString("mobile");
                            if(mobile.equals(mob)){
                                userId = obj1.getString("userid");
                            }
                        }
                    }
                }
            }
        }
        return userId;
    }
}
