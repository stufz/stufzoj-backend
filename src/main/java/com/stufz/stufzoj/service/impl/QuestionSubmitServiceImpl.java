package com.stufz.stufzoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stufz.stufzoj.common.ErrorCode;
import com.stufz.stufzoj.exception.BusinessException;
import com.stufz.stufzoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.stufz.stufzoj.model.entity.Question;
import com.stufz.stufzoj.model.entity.QuestionSubmit;
import com.stufz.stufzoj.model.entity.QuestionSubmit;
import com.stufz.stufzoj.model.entity.User;
import com.stufz.stufzoj.model.enums.QuestionSubmitLanguageEnum;
import com.stufz.stufzoj.model.enums.QuestionSubmitStatusEnum;
import com.stufz.stufzoj.service.QuestionService;
import com.stufz.stufzoj.service.QuestionSubmitService;
import com.stufz.stufzoj.service.QuestionSubmitService;
import com.stufz.stufzoj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author 10632
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-04-08 14:28:38
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //todo 检查编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum enumByValue = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (enumByValue==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }

        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }


        // 是否已提交题目
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmit.getCode());
        questionSubmit.setLanguage(language);
        //todo 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"插入数据失败");
        }
        return questionSubmit.getId();
    }


}




