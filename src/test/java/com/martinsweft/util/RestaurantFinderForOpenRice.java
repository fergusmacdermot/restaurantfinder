package com.martinsweft.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.martinsweft.business.location.LocationService;
import com.martinsweft.domain.Location;
import com.martinsweft.domain.LocationAttribute;

@Service
public class RestaurantFinderForOpenRice {

	private LocationService locationService;

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException, InterruptedException {
		ApplicationContext appContext = new FileSystemXmlApplicationContext(
				"/src/main/webapp/WEB-INF/application-context.xml");

		RestaurantFinderForOpenRice runner = new RestaurantFinderForOpenRice();
		runner.setLocationService((LocationService) appContext
				.getBean("locationService"));
		runner.runScaper();
	}

	private int total = 0;

	/**
	 * @param args
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws FailingHttpStatusCodeException
	 * @throws InterruptedException
	 */
	public void runScaper() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException, InterruptedException {

		final WebClient webClient = new WebClient();
		http://www.openrice.com/english/restaurant/sr2.htm?shopid=52892
		webClient.setThrowExceptionOnScriptError(false);
		webClient.setJavaScriptEnabled(false);
		webClient.setActiveXNative(false);
		webClient.setAppletEnabled(false);
		webClient.setCssEnabled(false);
		webClient.setPopupBlockerEnabled(true);
		webClient.setRedirectEnabled(false);
		webClient.setThrowExceptionOnFailingStatusCode(false);
		//page 4 failed at 7
		// page 29 failed at 11
		int pageNumber = 30;
		//up to 33
		for (int iterations = 0; iterations <  pageNumber; iterations++)
		{
			final HtmlPage contentPage = webClient
				.getPage("http://www.openrice.com/english/restaurant/sr1.htm?cuisine_id=2009&page="+pageNumber);
			System.out.println("page number:"+pageNumber);
//		final HtmlPage contentPage = webClient
//				.getPage("http://www.openrice.com/restaurant/sr1.htm?cuisine_id=2009");
			final List<?> divs = contentPage.getByXPath("//div[@class='sr1_list']");
		
			int counter = 0;
			for (Iterator i = divs.iterator(); i.hasNext();) {
				HtmlDivision div = (HtmlDivision) i.next();
				if (counter == 14) {
					break;
				}
				// now follow to the restaurant page as we want the Chinese too.
				String[] restaurantDetails = getRestaurantLink(div);
				Location english = new Location();
				int parentId = Integer.parseInt(pageNumber+""+counter);
				english.setParentId(parentId);
				english.setCountry("Hong Kong");
				english.setPostCode("empty");
				english.setLanguageCode("en_gb");
				english.setName(restaurantDetails[0]);
				english.addLocationAttribute(getLocationAttribute("openrice-link", "http://www.openrice.com" + restaurantDetails[1]));
				processRestaurantOtherInfo(div, english);
				System.out.println("saving:" + english);
				locationService.saveLocation(english);
				// now switch to the Chinese version
				String chineseLink = restaurantDetails[1].substring(8, restaurantDetails[1].length());
				System.out.println("calling:http://www.openrice.com"+chineseLink);
				final HtmlPage restaurantPage = webClient
				.getPage("http://www.openrice.com" + chineseLink);
				Location chinese = new Location();
				processRestaurantName(restaurantPage, chinese);
				processRestaurantAddressChinese(restaurantPage, chinese);
				chinese.setParentId(parentId);
				chinese.setCountry("香港");
				chinese.setPostCode("empty");
				chinese.setLatitude(english.getLatitude());
				chinese.setLongitude(english.getLongitude());
				chinese.setLanguageCode("zh_hk");
				chinese.setTelephone(english.getTelephone());
	
				chinese.addLocationAttribute(getLocationAttribute("openrice-link-chinese", "http://www.openrice.com" + chineseLink));
				getChineseFoodGenre( restaurantPage, chinese);
				System.out.println("saving chinese:" + chinese);
				locationService.saveLocation(chinese);
				counter++;
				System.out.println("finished, do next:"+counter);
			}
			pageNumber++;
		}
		return;

	}

	private void getChineseFoodGenre(HtmlPage page, Location chinese)
	{
		List<?> divs = page
				.getByXPath("//span[@class='blacklink']");
		//System.out.println("node again"+divs);
		for (Iterator i = divs.iterator(); i.hasNext();) {
			final HtmlSpan div = (HtmlSpan) i.next();
			//System.out.println("domList size" + div.asText());
			String str[] = StringUtils.split(
					div.asText(), "、");
			System.out.println(str.length);
			//StringBuilder sb = new StringBuilder();
			for (int m = 0; m < str.length; m++) {
				//getLocationAttribute("genre"+m,str[m]);
				//System.out.println(str[m]);
				chinese.addLocationAttribute(getLocationAttribute("genre"+m,str[m]));
			}			
		}
	}
	private String[] getRestaurantLink(HtmlDivision div) {
		DomNodeList<DomNode> domList = div.getChildNodes();
		String[] restaurantDetails = new String[2];
		for (DomNode i0 : domList) {
			//HTMLAnchor anchor = i0.getFirstChild().getFirstChild();
			DomNode domNode = i0.getFirstChild().getFirstChild();
			NamedNodeMap nodeMap = domNode.getAttributes();
			Node node = nodeMap.getNamedItem("href");
			restaurantDetails[0] = domNode.getTextContent();
			restaurantDetails[1] = node.getNodeValue();
			return restaurantDetails;
		}
		return restaurantDetails;
	}

	private void processRestaurantAddressChinese(HtmlPage page, Location chinese)
			throws FailingHttpStatusCodeException {
		
		DomNode nodal = page.getFirstByXPath("//*[@id='mainrestdetail']/div/div[2]/div[2]/table/tbody/tr/td[1]/table/tbody/tr[1]/td/div[2]/table[1]/tbody/tr/td[3]");
		int counter = 0;
		if (null != nodal)
		{
			for (DomNode node : nodal.getChildNodes()) {
				if (counter == 0)
				{
					chinese.setTown(node.getTextContent());
					//System.out.println(node.getTextContent());
				}
				if (counter == 1)
				{
					chinese.setAddressOne(node.getTextContent());
					chinese.setFullAddress(chinese.getTown()+node.getTextContent());
				}
				counter++;
			}
		}
	}

	private void processRestaurantName(HtmlPage page, 
			Location chinese) throws FailingHttpStatusCodeException {
		final List<?> divs = page
				.getByXPath("//span[@class='fn']");
		
		for (Iterator i = divs.iterator(); i.hasNext();) {
			final HtmlSpan div = (HtmlSpan) i.next();
			//System.out.println("domList size" + div.asText());
			chinese.setName(div.asText());
		}
	}
//	 this below processes the restaurant details from the contens page so has
//	 no other language info
	private void processRestaurantOtherInfo(HtmlDivision div, Location english)
			throws FailingHttpStatusCodeException {
		try {
			int i = 0;
			DomNodeList<DomNode> domList = div.getChildNodes();
			for (DomNode i0 : domList) {
				if (i == 1) {
					double latitude = Double.valueOf(i0.getFirstChild()
							.getAttributes().getNamedItem("x").getNodeValue());
					double longitude = Double.valueOf(i0.getFirstChild()
							.getAttributes().getNamedItem("y").getNodeValue());		
					english.setLatitude(latitude);
					english.setLongitude(longitude);				
				}
				if (i == 3) {

					DomNodeList<DomNode> addList = i0.getChildNodes();
					for (DomNode add : addList) {

						DomNodeList<DomNode> innerList = add.getChildNodes();
						int y = 0;
						for (DomNode address : innerList) {
							if (y == 0) {
								int z = 0;
								DomNodeList<DomNode> innerinnerList = address
										.getChildNodes();
								for (DomNode inneraddress : innerinnerList) {

									// System.out.println(z);
									if (z == 0) {
										if (inneraddress.asText().length() > 0) {
											// System.out.println("add:"+inneraddress.asText());
											String str[] = StringUtils.split(
													inneraddress.asText(), ",");
											// System.out.println(str.length);
											StringBuilder sb = new StringBuilder();
											for (int m = 0; m < str.length; m++) {
												if (m == str.length - 1) {
													break;
												}
												sb.append(str[m]);

											}
											english.setFullAddress(inneraddress
													.asText());
//											System.out.println("address:"
//													+ sb.toString());
											english.setAddressOne(sb
													.toString());
//											System.out.println("district:"
//													+ str[str.length - 1]);
											english.setTown(str[str.length - 1]);
										
										}
									}
									if (z == 1) {
										//final DomNodeList<DomNode> domList = span.getChildNodes();
										String[] genres = StringUtils.split(inneraddress.asText(), '|');
										for (int genreCount = 0;genreCount < genres.length; genreCount++)
										{
//											LocationAttribute attr = new LocationAttribute();
//											attr.setName("genre"+genreCount);
//											attr.setValue(genres[genreCount].trim());
											english.addLocationAttribute(getLocationAttribute("genre"+genreCount,genres[genreCount].trim()));
										}
//										System.out.println("genre:"
//												+ inneraddress.asText());
//										System.out.println("genre:"
//												+ genres.length);
									}
									if (z == 2) {
										String telephone = inneraddress.asText();
										System.out.println("tel:"
												+ telephone);
										english.setTelephone(telephone);
										//chinese.setTelephone(telephone);
									}
									z++;
								}
							}
							y++;
						}

					}
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private LocationAttribute getLocationAttribute(String name, String value)
	{
		LocationAttribute attr = new LocationAttribute();
		attr.setName(name);
		attr.setValue(value);	
		return attr;
	}
}
