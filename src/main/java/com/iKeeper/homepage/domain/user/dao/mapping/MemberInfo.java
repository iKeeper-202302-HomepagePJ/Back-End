package com.iKeeper.homepage.domain.user.dao.mapping;

import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Status;
import com.iKeeper.homepage.global.entity.Field;

public interface MemberInfo {

    String getStudentId();

    String getName();

    String getRole();

    Field getField();

    String getPnumber();

    String getBirth();

    String getEmail();

    Status getStatus();

    Grade getGrade();

    Major getMajor1();

    Major getMajor2();

    Major getMajor3();

    Major getMinor();

    Boolean getWarning();
}
