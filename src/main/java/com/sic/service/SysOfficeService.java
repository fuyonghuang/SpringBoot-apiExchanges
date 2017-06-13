package com.sic.service;

import java.util.List;

import com.sic.entity.SysOffice;

public interface SysOfficeService {
    List<SysOffice> selectByEntity(SysOffice sysOffice);

}
