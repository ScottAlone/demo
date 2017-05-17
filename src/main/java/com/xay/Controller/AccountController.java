package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Domain.AccountDomain;
import com.xay.Domain.GuideDomain;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.Security.AuthenticationRequest;
import com.xay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private BaseResult<Object> login(AuthenticationRequest authenticationRequest, Integer type){

        AccountDO accountDO;
        if (type == 0){
            accountDO = accountMapper.getGuideByUsername(authenticationRequest.getUsername());
        }else accountDO = accountMapper.getCustomerByUsername(authenticationRequest.getUsername());

        if (accountDO == null){
            return new BaseResult<>(500, "No user found");
        }
        // Perform the security
        if (passwordEncoder.matches(authenticationRequest.getPassword(), accountDO.getPassword())){
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
            Map<String, Object> map = new HashMap<>();
            map.put("name", accountDO.getName());
            map.put("username", accountDO.getUsername());
            map.put("type", type);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new BaseResult<>(map);
        }
        return new BaseResult<>(500, "Bad username or password");
    }

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
    public BaseResult<Object> authCustomer(@RequestBody AuthenticationRequest authenticationRequest){
        return login(authenticationRequest, 1);
    }

    /**
     * 导游登陆
     * @param authenticationRequest
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/sessions/guide", method = RequestMethod.POST)
    public BaseResult<Object> authGuide(@RequestBody AuthenticationRequest authenticationRequest){
        return login(authenticationRequest, 0);
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

    /**
     * 导游获取
     * @param cityName
     * @return
     */
    @RequestMapping(value = "/guides", method = RequestMethod.GET)
    public BaseResult<Object> getGuides(@RequestParam("cityName")String cityName){
        return accountService.getGuides(cityName);
    }

    /**
     * 导游信息获取
     * @param gUsername
     * @return
     */
    @RequestMapping(value = "/guides/guide", method = RequestMethod.GET)
    public BaseResult<Object> getGuideByName(@RequestParam("gUsername")String gUsername){
        return accountService.getGuideByName(gUsername);
    }

    /**
     * 修改地区和联系方式
     * @param guideDomain
     * @return
     */
    @RequestMapping(value = "/updateGuide", method = RequestMethod.PATCH)
    public BaseResult<Object> updateGuideInfo(@RequestBody GuideDomain guideDomain){
        return accountService.updateGuideInfo(guideDomain);
    }

    /**
     * 支付并评分
     * @param guideDomain
     * @return
     */
    @RequestMapping(value = "/payGuide", method = RequestMethod.PATCH)
    public BaseResult<Object> payGuide(@RequestBody GuideDomain guideDomain){
        return accountService.payGuide(guideDomain);
    }
}
