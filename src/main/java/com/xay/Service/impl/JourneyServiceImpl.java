package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.JourneyDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.MySQL.Mapper.JourneyMapper;
import com.xay.Service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@Service
public class JourneyServiceImpl implements JourneyService{

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private JourneyMapper journeyMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public BaseResult<Object> postJourney(JourneyDomain journeyDomain) {
        AccountDO accountDO = accountMapper.getCustomerByUsername(journeyDomain.getcUsername());
        if (accountDO != null){
            journeyMapper.postJourney(new JourneyDO(journeyDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No user found");
    }

    @Override
    public BaseResult<Object> getJourneyByCityName(String cityName) {
        JourneyDO[] journeyDOS = journeyMapper.getJourneyByCityName(cityName);
        JourneyDomain[] journeyDomains = new JourneyDomain[journeyDOS.length];
        if (journeyDomains.length == 0){
            return new BaseResult<>(500, "No journey found");
        }
        for (int i = 0; i < journeyDOS.length; i++){
            journeyDomains[i] = new JourneyDomain(journeyDOS[i], simpleDateFormat.format(journeyDOS[i].getCreate_time()), simpleDateFormat.format(journeyDOS[i].getUpdate_time()));
        }
        return new BaseResult<>(journeyDomains);
    }

    @Override
    public BaseResult<Object> getJourneyByUsername(String username){
        JourneyDO[] journeyDOS = journeyMapper.getJourneyByUsername(username);
        JourneyDomain[] journeyDomains = new JourneyDomain[journeyDOS.length];
        if (journeyDomains.length == 0){
            return new BaseResult<>(500, "No journey found");
        }
        for (int i = 0; i < journeyDOS.length; i++){
            journeyDomains[i] = new JourneyDomain(journeyDOS[i], simpleDateFormat.format(journeyDOS[i].getCreate_time()), simpleDateFormat.format(journeyDOS[i].getUpdate_time()));
        }
        return new BaseResult<>(journeyDomains);
    }

    @Override
    public BaseResult<Object> getJourneyByJourneyId(Integer journeyId) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyId);
        if (journeyDO != null) {
            return new BaseResult<>(new JourneyDomain(journeyDO, simpleDateFormat.format(journeyDO.getCreate_time()), simpleDateFormat.format(journeyDO.getCreate_time())));
        }
        return new BaseResult<>(500, "No journey found");
    }

    @Override
    public BaseResult<Object> selectJourney(Integer journeyId) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyId);
        if (journeyDO != null){
            journeyMapper.selectJourney(journeyId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No journey found");
    }
    @Override
    public BaseResult<Object> payJourney(Integer journeyId) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyId);
        if (journeyDO != null){
            journeyMapper.payJourney(journeyId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No journey found");
    }
}
