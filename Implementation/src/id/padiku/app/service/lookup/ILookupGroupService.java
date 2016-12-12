package id.padiku.app.service.lookup;

import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.LookupGroup;

import java.util.List;

public interface ILookupGroupService {
	public LookupGroup findByName(String name) throws SystemException;

	public abstract boolean checkUpdatableByLookupPK(Long pk)
			throws SystemException;

	public abstract boolean checkUpdatableByGroupName(String name)
			throws SystemException;

	public List<LookupGroup> findLookupGroupForCompany() throws SystemException;
}
