package kr.co.ezinfotech.ezcloud.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ezinfotech.ezcloud.domain.GoogleGeoCode;
import kr.co.ezinfotech.ezcloud.domain.GoogleGeoLatLng;
import kr.co.ezinfotech.ezcloud.domain.GoogleGeoResult;
import kr.co.ezinfotech.ezcloud.domain.PZDomain;
import kr.co.ezinfotech.ezcloud.domain.TF;
import kr.co.ezinfotech.ezcloud.domain.Term;
import kr.co.ezinfotech.ezcloud.service.PZService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller(value = "fileUploadController")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    
    @Autowired
	private PZService pzService;
    
    /**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
		String[] extension = file.getOriginalFilename().split("\\.");
		String tempFilename = timeStamp + "." + extension[1];
		
		File serverFile = null;
		
		if (validate(file)) {
			try {
				byte[] bytes = file.getBytes();			
				// 1. Creating the directory to store file
				ServletContext sc = request.getSession().getServletContext();
				File serverFolder = new File(sc.getRealPath("/resources/xlsxs"));		// original
				
				if (!serverFolder.exists()) {
					serverFolder.mkdirs();
				}					
				
				// 2. Create the file on server
				serverFile = new File(serverFolder + File.separator + tempFilename);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				// 3. Reading EXCEL file & check validate
				try {
					FileInputStream excelFile = new FileInputStream(new File(serverFile.toString()));
					Workbook workbook = new XSSFWorkbook(excelFile);
					Sheet datatypeSheet = workbook.getSheetAt(0);
					Iterator<Row> iterator = datatypeSheet.iterator();
	
					if(false == iterator.hasNext()) {
						throw new IOException("There is no data at first row");
					} else {
						iterator.next();
					}
					
					while (iterator.hasNext()) {
						Row currentRow = iterator.next();
						
						if(null == currentRow.getCell(0) || CellType.BLANK == currentRow.getCell(0).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(0).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(1) || CellType.BLANK == currentRow.getCell(1).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(1).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(2) || CellType.BLANK == currentRow.getCell(2).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(2).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(3) || CellType.BLANK == currentRow.getCell(3).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(3).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(4) || CellType.BLANK == currentRow.getCell(4).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(4).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(5) || CellType.BLANK == currentRow.getCell(5).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(5).getStringCellValue() + "|");
						}
						
						if(null == currentRow.getCell(6) || CellType.BLANK == currentRow.getCell(6).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(6).getNumericCellValue()) + "|");
						}
						if(null == currentRow.getCell(7) || CellType.BLANK == currentRow.getCell(7).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(7).getNumericCellValue()) + "|");
						}
						
						if(null == currentRow.getCell(8) || CellType.BLANK == currentRow.getCell(8).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(8).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(9) || CellType.BLANK == currentRow.getCell(9).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(9).getStringCellValue() + "|");
						}
						
						if(null == currentRow.getCell(10) || CellType.BLANK == currentRow.getCell(10).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(10).getDateCellValue() + "|");
						}
						if(null == currentRow.getCell(11) || CellType.BLANK == currentRow.getCell(11).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(11).getDateCellValue() + "|");
						}
						if(null == currentRow.getCell(12) || CellType.BLANK == currentRow.getCell(12).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(12).getDateCellValue() + "|");
						}
						if(null == currentRow.getCell(13) || CellType.BLANK == currentRow.getCell(13).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(13).getDateCellValue() + "|");
						}
						if(null == currentRow.getCell(14) || CellType.BLANK == currentRow.getCell(14).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(14).getDateCellValue() + "|");
						}
						if(null == currentRow.getCell(15) || CellType.BLANK == currentRow.getCell(15).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(15).getDateCellValue() + "|");
						}
						
						if(null == currentRow.getCell(16) || CellType.BLANK == currentRow.getCell(16).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(16).getStringCellValue() + "|");
						}
						
						if(null == currentRow.getCell(17) || CellType.BLANK == currentRow.getCell(17).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(17).getNumericCellValue()) + "|");
						}
						if(null == currentRow.getCell(18) || CellType.BLANK == currentRow.getCell(18).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(18).getNumericCellValue()) + "|");
						}
						if(null == currentRow.getCell(19) || CellType.BLANK == currentRow.getCell(19).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(19).getNumericCellValue()) + "|");
						}
						if(null == currentRow.getCell(20) || CellType.BLANK == currentRow.getCell(20).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(20).getNumericCellValue()) + "|");
						}
						
						if(null == currentRow.getCell(21) || CellType.BLANK == currentRow.getCell(21).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(21).getNumericCellValue()) + "|");
						}
						if(null == currentRow.getCell(22) || CellType.BLANK == currentRow.getCell(22).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(22).getNumericCellValue()) + "|");
						}
						if(null == currentRow.getCell(23) || CellType.BLANK == currentRow.getCell(23).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(String.valueOf((int)currentRow.getCell(23).getNumericCellValue()) + "|");
						}
						
						if(null == currentRow.getCell(24) || CellType.BLANK == currentRow.getCell(24).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(24).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(25) || CellType.BLANK == currentRow.getCell(25).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(25).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(26) || CellType.BLANK == currentRow.getCell(26).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(26).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(27) || CellType.BLANK == currentRow.getCell(27).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(27).getStringCellValue() + "|");
						}
						if(null == currentRow.getCell(28) || CellType.BLANK == currentRow.getCell(28).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(28).getNumericCellValue() + "|");
						}
						if(null == currentRow.getCell(29) || CellType.BLANK == currentRow.getCell(29).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(29).getNumericCellValue() + "|");
						}
						if(null == currentRow.getCell(30) || CellType.BLANK == currentRow.getCell(30).getCellTypeEnum()) {
							System.out.print("|");
						} else {
							System.out.print(currentRow.getCell(30).getDateCellValue() + "|");
						}
						
						System.out.println();
			        }
					workbook.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					serverFile.delete();
					return "You failed to upload " + tempFilename + " => " + e.getMessage();
				} catch (IOException e) {
					e.printStackTrace();
					serverFile.delete();
					return "You failed to upload " + tempFilename + " => " + e.getMessage();
				}
				
				// 4. insert DB
				try {
					FileInputStream excelFile = new FileInputStream(new File(serverFile.toString()));
					Workbook workbook = new XSSFWorkbook(excelFile);
					Sheet datatypeSheet = workbook.getSheetAt(0);
					Iterator<Row> iterator = datatypeSheet.iterator();
					
					if(false == iterator.hasNext()) {
						throw new IOException("There is no data at first row");
					} else {
						iterator.next();
					}
					
					while (iterator.hasNext()) {
						Row currentRow = iterator.next();
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						DateFormat dfForTime = new SimpleDateFormat("HH:mm:ss");
						String dateToString = null;
						
						PZDomain pz = new PZDomain();
						if(null == currentRow.getCell(0) || CellType.BLANK == currentRow.getCell(0).getCellTypeEnum()) {
							pz.setNo("");
						} else {
							pz.setNo(currentRow.getCell(0).getStringCellValue());
						}
						if(null == currentRow.getCell(1) || CellType.BLANK == currentRow.getCell(1).getCellTypeEnum()) {
							pz.setName("");
						} else {
							pz.setName(currentRow.getCell(1).getStringCellValue());
						}
						if(null == currentRow.getCell(2) || CellType.BLANK == currentRow.getCell(2).getCellTypeEnum()) {
							pz.setDivision("");
						} else {
							pz.setDivision(currentRow.getCell(2).getStringCellValue());
						}
						if(null == currentRow.getCell(3) || CellType.BLANK == currentRow.getCell(3).getCellTypeEnum()) {
							pz.setType("");
						} else {
							pz.setType(currentRow.getCell(3).getStringCellValue());
						}
					    if(null == currentRow.getCell(4) || CellType.BLANK == currentRow.getCell(4).getCellTypeEnum()) {
					    	pz.setAddr_road("");
					    } else {
					    	pz.setAddr_road(currentRow.getCell(4).getStringCellValue());
					    }
					    if(null == currentRow.getCell(5) || CellType.BLANK == currentRow.getCell(5).getCellTypeEnum()) {
					    	pz.setAddr_jibun("");
					    } else {
					    	pz.setAddr_jibun(currentRow.getCell(5).getStringCellValue());
					    }
					    
					    if(null == currentRow.getCell(6) || CellType.BLANK == currentRow.getCell(6).getCellTypeEnum()) {
					    	pz.setTotal_p("");
					    } else {
					    	pz.setTotal_p(Double.toString(currentRow.getCell(6).getNumericCellValue()));
					    }
					    if(null == currentRow.getCell(7) || CellType.BLANK == currentRow.getCell(7).getCellTypeEnum()) {
					    	pz.setFeed("");
					    } else {
					    	pz.setFeed(Double.toString(currentRow.getCell(7).getNumericCellValue()));
					    }
					    
					    if(null == currentRow.getCell(8) || CellType.BLANK == currentRow.getCell(8).getCellTypeEnum()) {
					    	pz.setBuje("");
					    } else {
					    	pz.setBuje(currentRow.getCell(8).getStringCellValue());
					    }
					    if(null == currentRow.getCell(9) || CellType.BLANK == currentRow.getCell(9).getCellTypeEnum()) {
					    	pz.setOp_date("");
					    } else {
					    	pz.setOp_date(currentRow.getCell(9).getStringCellValue());
					    }
					    
					    Term tmpTermForWop = new Term();
					    if(null == currentRow.getCell(10) || CellType.BLANK == currentRow.getCell(10).getCellTypeEnum()) {
					    	//pz.setW_op_start_date("");
					    	tmpTermForWop.setStart_date("");
					    } else {
					    	dateToString = dfForTime.format(currentRow.getCell(10).getDateCellValue());
					    	//pz.setW_op_start_date(dateToString);
					    	tmpTermForWop.setStart_date(dateToString);
					    }
					    if(null == currentRow.getCell(11) || CellType.BLANK == currentRow.getCell(11).getCellTypeEnum()) {
					    	//pz.setW_op_end_date("");
					    	tmpTermForWop.setEnd_date("");
					    } else {
					    	dateToString = dfForTime.format(currentRow.getCell(11).getDateCellValue());
						    //pz.setW_op_end_date(dateToString);
					    	tmpTermForWop.setEnd_date(dateToString);
					    }
					    pz.setW_op(tmpTermForWop);
					    
					    Term tmpTermForSop = new Term();
					    if(null == currentRow.getCell(12) || CellType.BLANK == currentRow.getCell(12).getCellTypeEnum()) {
					    	//pz.setS_op_start_date("");
					    	tmpTermForSop.setStart_date("");
					    } else {
					    	dateToString = dfForTime.format(currentRow.getCell(12).getDateCellValue());
					    	//pz.setS_op_start_date(dateToString);
					    	tmpTermForSop.setStart_date(dateToString);
					    }
					    if(null == currentRow.getCell(13) || CellType.BLANK == currentRow.getCell(13).getCellTypeEnum()) {
					    	//pz.setS_op_end_date("");
					    	tmpTermForSop.setEnd_date("");
					    } else {
					    	dateToString = dfForTime.format(currentRow.getCell(13).getDateCellValue());
					    	//pz.setS_op_end_date(dateToString);
					    	tmpTermForSop.setEnd_date(dateToString);
					    }
					    pz.setS_op(tmpTermForSop);
					    
					    Term tmpTermForHop = new Term();
					    if(null == currentRow.getCell(14) || CellType.BLANK == currentRow.getCell(14).getCellTypeEnum()) {
					    	//pz.setH_op_start_date("");
					    	tmpTermForHop.setStart_date("");
					    } else {
					    	dateToString = dfForTime.format(currentRow.getCell(14).getDateCellValue());
						    //pz.setH_op_start_date(dateToString);
					    	tmpTermForHop.setStart_date(dateToString);
					    }
					    if(null == currentRow.getCell(15) || CellType.BLANK == currentRow.getCell(15).getCellTypeEnum()) {
					    	//pz.setH_op_end_date("");
					    	tmpTermForHop.setEnd_date("");
					    } else {
					    	dateToString = dfForTime.format(currentRow.getCell(15).getDateCellValue());
					    	//pz.setH_op_end_date(dateToString);
					    	tmpTermForHop.setEnd_date(dateToString);
					    }
					    pz.setH_op(tmpTermForHop);
					    
					    if(null == currentRow.getCell(16) || CellType.BLANK == currentRow.getCell(16).getCellTypeEnum()) {
					    	pz.setFee_info("");
					    } else {
					    	pz.setFee_info(currentRow.getCell(16).getStringCellValue());
					    }
					    
					    TF tmpTFForParkbase = new TF();
					    if(null == currentRow.getCell(17) || CellType.BLANK == currentRow.getCell(17).getCellTypeEnum()) {
					    	//pz.setPark_base_time("");
					    	tmpTFForParkbase.setTime("");
					    } else {
					    	//pz.setPark_base_time(Double.toString(currentRow.getCell(17).getNumericCellValue()));
					    	tmpTFForParkbase.setTime(Double.toString(currentRow.getCell(17).getNumericCellValue()));
					    }
					    if(null == currentRow.getCell(18) || CellType.BLANK == currentRow.getCell(18).getCellTypeEnum()) {
					    	//pz.setPark_base_fee("");
					    	tmpTFForParkbase.setFee("");
					    } else {
					    	//pz.setPark_base_fee(Double.toString(currentRow.getCell(18).getNumericCellValue()));
					    	tmpTFForParkbase.setFee(Double.toString(currentRow.getCell(18).getNumericCellValue()));
					    }
					    pz.setPark_base(tmpTFForParkbase);
					    
					    TF tmpTFForAddTerm = new TF();
					    if(null == currentRow.getCell(19) || CellType.BLANK == currentRow.getCell(19).getCellTypeEnum()) {
					    	//pz.setAdd_term_time("");
					    	tmpTFForAddTerm.setTime("");
					    } else {
					    	//pz.setAdd_term_time(Double.toString(currentRow.getCell(19).getNumericCellValue()));
					    	tmpTFForAddTerm.setTime(Double.toString(currentRow.getCell(19).getNumericCellValue()));
					    }
					    if(null == currentRow.getCell(20) || CellType.BLANK == currentRow.getCell(20).getCellTypeEnum()) {
					    	//pz.setAdd_term_fee("");
					    	tmpTFForAddTerm.setFee("");
					    } else {
					    	//pz.setAdd_term_fee(Double.toString(currentRow.getCell(20).getNumericCellValue()));
					    	tmpTFForAddTerm.setFee(Double.toString(currentRow.getCell(20).getNumericCellValue()));
					    }
					    pz.setAdd_term(tmpTFForAddTerm);
					    
					    TF tmpTFForOneDayPark = new TF();
					    if(null == currentRow.getCell(21) || CellType.BLANK == currentRow.getCell(21).getCellTypeEnum()) {
					    	//pz.setOne_day_park_time("");
					    	tmpTFForOneDayPark.setTime("");
					    } else {
					    	//pz.setOne_day_park_time(Double.toString(currentRow.getCell(21).getNumericCellValue()));
					    	tmpTFForOneDayPark.setTime(Double.toString(currentRow.getCell(21).getNumericCellValue()));
					    }
					    if(null == currentRow.getCell(22) || CellType.BLANK == currentRow.getCell(22).getCellTypeEnum()) {
					    	//pz.setOne_day_park_fee("");
					    	tmpTFForOneDayPark.setFee("");
					    } else {
					    	//pz.setOne_day_park_fee(Double.toString(currentRow.getCell(22).getNumericCellValue()));
					    	tmpTFForOneDayPark.setFee(Double.toString(currentRow.getCell(22).getNumericCellValue()));
					    }
					    pz.setOne_day_park(tmpTFForOneDayPark);
					    
					    if(null == currentRow.getCell(23) || CellType.BLANK == currentRow.getCell(23).getCellTypeEnum()) {
					    	pz.setMonth_fee("");
					    } else {
					    	pz.setMonth_fee(Double.toString(currentRow.getCell(23).getNumericCellValue()));
					    }
					    
					    if(null == currentRow.getCell(24) || CellType.BLANK == currentRow.getCell(24).getCellTypeEnum()) {
					    	pz.setPayment("");
					    } else {
					    	pz.setPayment(currentRow.getCell(24).getStringCellValue());
					    }
					    if(null == currentRow.getCell(25) || CellType.BLANK == currentRow.getCell(25).getCellTypeEnum()) {
					    	pz.setRemarks("");
					    } else {
					    	pz.setRemarks(currentRow.getCell(25).getStringCellValue());
					    }
					    if(null == currentRow.getCell(26) || CellType.BLANK == currentRow.getCell(26).getCellTypeEnum()) {
					    	pz.setManager("");
					    } else {
					    	pz.setManager(currentRow.getCell(26).getStringCellValue());
					    }
					    if(null == currentRow.getCell(27) || CellType.BLANK == currentRow.getCell(27).getCellTypeEnum()) {
					    	pz.setTel("");
					    } else {
					    	pz.setTel(currentRow.getCell(27).getStringCellValue());
					    }
					    
					    GoogleGeoLatLng GGLL = null;
					    if(null == currentRow.getCell(28) || CellType.BLANK == currentRow.getCell(28).getCellTypeEnum()) {
					    	//pz.setLat("");
					    	GGLL = getMostProbableLocation(pz.getAddr_road(), getGeoCode(pz.getAddr_road(), true));
					    	pz.setLat(GGLL.getLat());
					    } else {
					    	pz.setLat(Double.toString(currentRow.getCell(28).getNumericCellValue()));
					    }
					    if(null == currentRow.getCell(29) || CellType.BLANK == currentRow.getCell(29).getCellTypeEnum()) {
					    	//pz.setLng("");
					    	if(null == GGLL) {
					    		GGLL = getMostProbableLocation(pz.getAddr_road(), getGeoCode(pz.getAddr_road(), true));
					    	}
					    	pz.setLng(GGLL.getLng());
					    } else {
					    	pz.setLng(Double.toString(currentRow.getCell(29).getNumericCellValue()));
					    }
					    
					    if(null == currentRow.getCell(30) || CellType.BLANK == currentRow.getCell(30).getCellTypeEnum()) {
					    	pz.setData_date("");
					    } else {
					    	dateToString = df.format(currentRow.getCell(30).getDateCellValue());
					    	pz.setData_date(dateToString);
					    }
					   
						pzService.insert(pz);
						logger.info("pz insert : "  + pz.toString());
					}
					workbook.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					serverFile.delete();
					return "You failed to insert DB " + tempFilename + " => " + e.getMessage();
				} catch (IOException e) {
					e.printStackTrace();
					serverFile.delete();
					return "You failed to insert DB " + tempFilename + " => " + e.getMessage();
				}
				
				logger.info("Server File Location=" + serverFile.getCanonicalPath());
				serverFile.delete();	// Delete successful process file!
				
				return "You successfully processing file=" + tempFilename;
			} catch (Exception e) {
				serverFile.delete();
				//return "You failed to upload " + tempFilename + " => " + e.getMessage() + ", Error at (" + (accessRowIdx+1) + ", " + accessColIdx + ")";
				return "You failed to upload " + tempFilename + " => " + e.getMessage() + ")";
			}
		} else {
			//serverFile.delete();
			return "You failed to upload " + tempFilename + " because the file was empty or wrong file type.";
		}
	}
	
	/**
	 * Validate upload file type
	 */
	public boolean validate(Object uploadedFile) {
		MultipartFile file = (MultipartFile) uploadedFile;
		
		if (file.isEmpty() || file.getSize() == 0) {
			logger.info("file is empty || getsize() == 0");
			return false;
		}
		
		if (!(file.getContentType().toLowerCase().equals("application/vnd.ms-excel")
					|| file.getContentType().toLowerCase().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
					|| file.getContentType().toLowerCase().equals("application/haansoftxlsx"))) {
			logger.info("file type: " + file.getContentType().toLowerCase());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Validate string length
	 */
	public boolean checkStringLength(int dbLength, int xlsxLength) {
		if(dbLength < xlsxLength) {
			return false;
		}
		return true;
	}
	
	///////////////////////////////////// https://halexv.blogspot.kr/2015/07/java-geocoding-using-google-maps-api.html ///////////////////////////////////
	/**
	 * Given an address asks google for geocode
	 * 
	 * If ssl is true API_KEY should be a valid developer key (given by google)
	 *
	 * @param address the address to find
	 * @param ssl defines if ssl should be used
	 * @return the GoogleGeoCode found
	 * @throws Exception in case of any error
	 *
	 */
	public GoogleGeoCode getGeoCode(String address, boolean ssl) throws Exception {
	    // build url
	    StringBuilder url = new StringBuilder("http");
	    if ( ssl ) {
	        url.append("s");
	    }
	   
	    url.append("://maps.googleapis.com/maps/api/geocode/json?");
	   
	    if ( ssl ) {
	        url.append("key=");
	        url.append("AIzaSyAbcu_ORn9DV9mv0GFbxwX3FrYFMyL-nRA");
	        url.append("&");
	    }
	    url.append("sensor=false&address=");
	    url.append( URLEncoder.encode(address, "UTF-8") );
	   
	    logger.info("GoogleGeoCode URL - " + url.toString());
	    // request url like: http://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address) + "&sensor=false"
	    // do request
	    try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
	        HttpGet request = new HttpGet(url.toString());

	        // set common headers (may useless)
	        request.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0 Iceweasel/31.6.0");
	        request.setHeader("Host", "maps.googleapis.com");
	        request.setHeader("Connection", "keep-alive");
	        request.setHeader("Accept-Language", "en-US,en;q=0.5");
	        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        request.setHeader("Accept-Encoding", "gzip, deflate");

	        try (CloseableHttpResponse response = httpclient.execute(request)) {
	            HttpEntity entity = response.getEntity();

	            // recover String response (for debug purposes)
	            StringBuilder result = new StringBuilder();
	            try (BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()))) {
	                String inputLine;
	                while ((inputLine = in.readLine()) != null) {
	                    result.append(inputLine);
	                    result.append("\n");
	                }
	            }

	            // parse result
	            ObjectMapper mapper = new ObjectMapper();
	            GoogleGeoCode geocode = mapper.readValue(result.toString(), GoogleGeoCode.class);

	            if (!"OK".equals(geocode.getStatus())) {
	                if (geocode.getError_message() != null) {
	                    //throw new Exception(geocode.getError_message());
	                	logger.error(geocode.getError_message());
	                }
	                logger.error("Can not find geocode for: " + address);
	                //throw new Exception("Can not find geocode for: " + address);
	            }
	            return geocode;
	        }
	    }
	}
	
	/**
	 * Given an address and google geocode find the most probable location of
	 * address, the measure uses the longest common subsequence algorithm and a
	 * minimum requirement for similarity
	 *
	 * @param address the original address
	 * @param geocode the google geocode results
	 * @return the most probable location (lat, lng), null if no one matches
	 */
	public GoogleGeoLatLng getMostProbableLocation(String address, GoogleGeoCode geocode) {
		/* ORIGINAL
	    address = address.toLowerCase();
	    int expected = address.length() / 2;
	    int sz = geocode.getResults().length;
	    int best = expected;
	    GoogleGeoLatLng latlng = null;
	    for (GoogleGeoResult result : geocode.getResults()) {
	        GoogleGeoLatLng cur = result.getGeometry().getLocation();
	        String formattedAddress = result.getFormatted_address().toLowerCase();
	        int p = lcs(address, formattedAddress);

	        if (p > best) {
	            latlng = cur;
	            best = p;
	        } 
	    }
	    return latlng;
	    */
		if("ZERO_RESULTS".equals(geocode.getStatus())) {
			GoogleGeoLatLng tmpGGLL = new GoogleGeoLatLng();
			tmpGGLL.setLat("0");
			tmpGGLL.setLng("0");
			return tmpGGLL;
		} else {
			GoogleGeoResult[] result = geocode.getResults();
			return result[0].getGeometry().getLocation();
		}
	}
	
	/**
	 * The longest common subsequence of s and t using dynamic programming
	 *
	 * @param s the first string
	 * @param t the second string
	 * @return the length of the longest common subsequence
	 */
	private int lcs(String s, String t) {
	    int N = s.length();
	    int M = t.length();
	    int[][] ans = new int[N + 1][M + 1];
	    for (int k = N - 1; k >= 0; k--) {
	        for (int m = M - 1; m >= 0; m--) {
	            if (s.charAt(k) == t.charAt(m)) {
	                ans[k][m] = 1 + ans[k + 1][m + 1];
	            } else {
	                ans[k][m] = Math.max(ans[k + 1][m], ans[k][m + 1]);
	            }
	        }
	    }
	    return ans[0][0];
	}
	///////////////////////////////////// https://halexv.blogspot.kr/2015/07/java-geocoding-using-google-maps-api.html ///////////////////////////////////
		
}
