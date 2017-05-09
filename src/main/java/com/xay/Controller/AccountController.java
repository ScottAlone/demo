package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Domain.AccountDomain;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.Security.AuthenticationRequest;
import com.xay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/4.
 */
@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 用户注册
     * @param account
     * @return
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public BaseResult<Object> register(@RequestBody AccountDomain account){
        return accountService.register(account);
    }

    /**
     * 客户登陆
     * @param authenticationRequest
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/sessions/customer", method = RequestMethod.POST)
    public BaseResult<Object> authCustomer(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (accountMapper.getCustomerByUsername(authenticationRequest.getUsername()) != null){
            return new BaseResult<>();
        }else return new BaseResult<>(500, "用户类型错误");
    }

    /**
     * 导游登陆
     * @param authenticationRequest
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/sessions/guide", method = RequestMethod.POST)
    public BaseResult<Object> authGuide(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (accountMapper.getGuideByUsername(authenticationRequest.getUsername()) != null){
            return new BaseResult<>();
        }else return new BaseResult<>(500, "用户类型错误");
    }

    /**
     * 用户昵称及密码修改
     * @param account
     * @return
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.PATCH)
    public BaseResult<Object> update(@RequestBody AccountDomain account) throws NoSuchAlgorithmException{
        return accountService.update(account);
    }

    /**
     * 上传头像
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public BaseResult<Object> insertImage(HttpServletRequest request) throws IOException{
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String type = request.getParameter("type");
        String f = request.getParameter("file");
        AccountDomain accountDomain;
        if (!f.contains(",")){
            accountDomain = new AccountDomain(username, Integer.valueOf(type), f.getBytes());
            return accountService.insertImage(accountDomain);
        }
        String[] t = f.split(",");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] file = decoder.decodeBuffer(t[1]);
        accountDomain = new AccountDomain(username, Integer.valueOf(type), file);
        return accountService.insertImage(accountDomain);
    }

    /**
     * 头像获取
     * @param username
     * @param type
     * @return
     */
    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public BaseResult<Object> getImage(@RequestParam("username")String username, @RequestParam("type")Integer type){
        return accountService.getImage(username, type);
    }
}
