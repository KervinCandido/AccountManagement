package com.github.kervincandido.accountmanagement.usecase.rule.register;

import com.github.kervincandido.accountmanagement.domain.entity.Email;
import com.github.kervincandido.accountmanagement.domain.entity.User;
import com.github.kervincandido.accountmanagement.usecase.exception.BusinessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class PasswordPatternRuleTest {

    @InjectMocks
    private PasswordPatternRule passwordPatternRule;

    private AutoCloseable mockitoAnnotations;

    @BeforeEach
    void setUp() {
        mockitoAnnotations = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoAnnotations.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "<Vx2!owq", "f(m,UJ(4", "a'6IB,w1", "7TIW>g|j", "BEiC=FJ8", "&U7Li,AW@x", "0Y9y(YviC^", "mkUGq%I9{&",
            "hd{RNm0AMQ", "[1@Qku(X0O", "zQbV/qE[2N", "nL9z1>)!!}", "Xd2Xj25,yR" ,"5ujQv-'9Q!ar", "f`A;%mkzt;45",
            "w_2aa;i.E805ox>{", "9xDYqPj'(BnXP*5V", "v;T{12Dj=NI<}Yp9", "xTZ4=JU(O/559V2w^^", "<1*XI(q4KdtGD=3;e'",
            "Cd&DdKE3>,ku:{0L4@", "9Cjv8ih=!AG9XmZ/lj,", "'Ji8WcW4B,O^'7&g/2/", "K1df¨as8", "Pa55&W0rd", "O#asf205"
    })
    void strongPassword(String password) {
        var user = new User("tester", password, new Email("tester@email.com"));
        Assertions.assertDoesNotThrow(() -> passwordPatternRule.validate(user));
    }

    @ParameterizedTest
    @ValueSource(strings = {"secret123", "weakPassword", ";=8Qyt", "pass123", "g1602ieo", "qOBBaW0I"})
    void weakPassword(String password) {
        var user = new User("tester", password, new Email("tester@email.com"));
        Assertions.assertThrows(BusinessException.class, () -> passwordPatternRule.validate(user));
    }

    @Test
    void tooLongPassword() {
        var user = new User("tester", "8MlhM4~jX*uX{L9L{cN~Z2,(nNU^Gk", new Email("tester@email.com"));
        Assertions.assertThrows(BusinessException.class, () -> passwordPatternRule.validate(user));
    }
}