package id.base.app.service.frontEndDisplay;

import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.setting.ISettingDAO;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.HomeSettingHelper;
import id.base.app.valueobject.frontend.Setting;
import id.base.app.valueobject.frontend.SettingArticleHelper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingService implements ISettingService {

	@Autowired
	private ISettingDAO settingDAO;
	
	@Override
	public Setting findById(Long id) throws SystemException {
		return settingDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(Setting anObject) throws SystemException {
		settingDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<Setting> findObjects(Long[] objectPKs) throws SystemException {
		List<Setting> objects = new ArrayList<>();
		Setting object = null;
		for(Long l:objectPKs){
			object = settingDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<Setting> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return settingDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Setting> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return settingDAO.findAll(filter, order);
	}
	
	@Override
	public List<Setting> findAllSetting(List<Integer> typeList) throws SystemException {
		return settingDAO.findAllSetting(typeList);
	}
	
	@Override
	public HomeSettingHelper convertToHomeSetting(List<Setting> settingList) throws SystemException {
		HomeSettingHelper hsh = new HomeSettingHelper();
		for (Setting setting : settingList) {
			if(SystemConstant.SettingType.HOME_BANNER.equals(setting.getType())){
				hsh.setHomeBanner(setting.getValue());
			}else if(SystemConstant.SettingType.HOME_TOP_INFO.equals(setting.getType())){
				hsh.setHomeTopInfo1(setting.getLabel1());
				hsh.setHomeTopInfo2(setting.getLabel2());
			}else if(SystemConstant.SettingType.HOME_TOP_INFO_STEP.equals(setting.getType())){
				List<SettingArticleHelper> sahList = hsh.getHomeTopInfoStep();
				if(sahList==null){
					sahList = new ArrayList<>();
				}
				sahList.add(SettingArticleHelper.getInstance(null, setting.getValue(), setting.getLabel1(), setting.getOrderNo()));
				hsh.setHomeTopInfoStep(sahList);
			}else if(SystemConstant.SettingType.HOME_WHO_AM_I.equals(setting.getType())){
				hsh.setHomeWhoAmI(setting.getLabel1());
			}else if(SystemConstant.SettingType.HOME_WHO_AM_I_RIGHT.equals(setting.getType())){
				List<SettingArticleHelper> sahList = hsh.getHomeWhoAmRight();
				if(sahList==null){
					sahList = new ArrayList<>();
				}
				sahList.add(SettingArticleHelper.getInstance(setting.getLabel1(), setting.getValue(), null, setting.getOrderNo()));
				hsh.setHomeWhoAmRight(sahList);
			}else if(SystemConstant.SettingType.HOME_WHO_AM_I_F_CAPITAL.equals(setting.getType())){
				hsh.setHomeWhoAmIFCapital(setting.getLabel1());
			}else if(SystemConstant.SettingType.HOME_WHO_AM_I_F_LAND.equals(setting.getType())){
				hsh.setHomeWhoAmIFLand(setting.getLabel1());
			}else if(SystemConstant.SettingType.HOME_WHO_AM_I_F_LEGAL.equals(setting.getType())){
				hsh.setHomeWhoAmIFLegal(setting.getLabel1());
			}else if(SystemConstant.SettingType.HOME_WHO_AM_I_F_SKILLS.equals(setting.getType())){
				hsh.setHomeWhoAmIFSkills(setting.getLabel1());
			}else if(SystemConstant.SettingType.HOME_NEWS.equals(setting.getType())){
				hsh.setHomeNews(setting.getLabel1());
			}else if(SystemConstant.SettingType.HOME_TESTIMONIAL.equals(setting.getType())){
				hsh.setHomeTestimonial(setting.getLabel1());
			}
		}
		return hsh;
	}
	
	

}