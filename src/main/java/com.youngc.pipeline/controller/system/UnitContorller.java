package com.youngc.pipeline.controller.system;


import com.youngc.pipeline.bean.param.UnitBean;
import com.youngc.pipeline.model.UnitModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.UnitService;
import com.youngc.pipeline.service.system.UserManagerService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unit")
public class UnitContorller {
        @Autowired
        private UnitService unitService;

        /**
         * 查询单位信息
         *
         * @param keyword
         * @param pageNum
         * @param pageSize
         * @return
         */
        @GetMapping
        public Result getUnitList(@RequestParam String keyword, @RequestParam int pageNum, @RequestParam int pageSize) {
                return ResultGenerator.generate(unitService.getList(keyword, pageNum, pageSize));
        }

        @GetMapping("/{userId}")
        public Result getUnitDetails(@PathVariable Long userId) {
                return ResultGenerator.generate(ResultCode.SUCCESS, unitService.getUserDetails(userId));
        }

        /**
         * 修改单位信息
         *
         * @param unitBean
         * @return
         */
        @PutMapping
        public Result putUser(@RequestBody UnitBean unitBean) {
                com.youngc.pipeline.bean.context.UserBean user
                        = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
                UnitModel unitModel = new UnitModel();
                unitModel.setUnitId(unitBean.getUnitId());
                unitModel.setOrgId(unitBean.getOrgId());
                unitModel.setAddress(unitBean.getAddress());
                unitModel.setContactOne(unitBean.getContactOne());
                unitModel.setContactTwo(unitBean.getContactTwo());
                unitModel.setEmail(unitBean.getEmail());
                unitModel.setPhoneOne(unitBean.getPhoneOne());
                unitModel.setPhoneTwo(unitBean.getPhoneTwo());
                unitModel.setRemark(unitBean.getRemark());
                unitModel.setUnitName(unitBean.getUnitName());
                unitModel.setUnitCode(unitBean.getUnitCode());

                return ResultGenerator.generate(ResultCode.SUCCESS, unitService.updateUnitDetails(unitModel));
        }

        /**
         * 添加单位信息
         *
         * @param unitBean
         * @return
         */
        @PostMapping
        public Result postUser(@RequestBody UnitBean unitBean) {
                com.youngc.pipeline.bean.context.UserBean user
                        = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
                UnitModel unitModel = new UnitModel();
                unitModel.setOrgId(unitBean.getOrgId());
                unitModel.setAddress(unitBean.getAddress());
                unitModel.setContactOne(unitBean.getContactOne());
                unitModel.setContactTwo(unitBean.getContactTwo());
                unitModel.setEmail(unitBean.getEmail());
                unitModel.setPhoneOne(unitBean.getPhoneOne());
                unitModel.setPhoneTwo(unitBean.getPhoneTwo());
                unitModel.setRemark(unitBean.getRemark());
                unitModel.setUnitName(unitBean.getUnitName());
                unitModel.setUnitCode(unitBean.getUnitCode());
                unitModel.setAddPerson(user.getUserId());
                unitModel.setLastPerson(user.getUserId());

                return ResultGenerator.generate(ResultCode.SUCCESS, unitService.addUnit(unitModel));
        }

        /**
         * 删除单位
         * @param unitId
         * @return
         */
        @DeleteMapping
        public Result deleteUserList(@RequestParam("unitId") String unitId) {
                unitService.deleteUnitList(unitId);
                return ResultGenerator.generate(ResultCode.SUCCESS);
        }
}