package com.ccw.testonline.common.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * 
 * Title: testonline <br>
 * Description: 读出权限<br>
 * 
 * @author <a href="mailto:邮箱">陈炽伟</a><br>
 * @e-mail: chencw@eastcom-sw.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月21日 下午3:36:15 <br>
 * 
 */
public class PrivilegeUtils {

	public Map<String, List<String>> read() throws DocumentException {
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		Document doc;
		URL path = this.getClass().getClassLoader()
				.getResource("/privilege.xml");
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			doc = sr.read(path.getPath());
			// 得到xml所在位置。然后开始读取。并将数据放入doc中
			Element el_root = doc.getRootElement();// 向外取数据，获取xml的根节点。
			Iterator<?> root = el_root.elementIterator();// 从根节点下依次遍历，获取根节点下所有子节点

			while (root.hasNext()) {// 遍历该子节点
				List<String> privilege = new ArrayList<String>();
				Element role = (Element) root.next();// 再获取该子节点下的子节点

				String roleName = role.getName();

				Iterator<?> resources = role.elementIterator();

				while (resources.hasNext()) {// 遍历节点

					Element resource = (Element) resources.next();// 获取该节点下的所有数据。
					privilege.add(resource.getText());
				}
				map.put(roleName, privilege);
			}
		} catch (DocumentException e) {
			throw e;
		}
		return map;
	}

	public List<String> readPrivilege(Integer type) throws DocumentException {
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		Document doc;
		URL path = this.getClass().getClassLoader()
				.getResource("/privilege.xml");
		List<String> privilege = null;
		try {
			doc = sr.read(path.getPath());
			// 得到xml所在位置。然后开始读取。并将数据放入doc中
			Element el_root = doc.getRootElement();// 向外取数据，获取xml的根节点。
			Iterator<?> root = el_root.elementIterator();// 从根节点下依次遍历，获取根节点下所有子节点
			while (root.hasNext()) {// 遍历该子节点

				Element role = (Element) root.next();// 再获取该子节点下的子节点
				Attribute attribute = role.attribute("type");
				Integer roleType = Integer.parseInt(attribute.getValue());

				if (type == roleType) {
					Iterator<?> resources = role.elementIterator();
					privilege = new ArrayList<String>();
					while (resources.hasNext()) {// 遍历节点
						Element resource = (Element) resources.next();// 获取该节点下的所有数据。
						privilege.add(resource.getText());
					}
				}

			}
		} catch (DocumentException e) {
			throw e;
		}
		return privilege;
	}
}
