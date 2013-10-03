package jdbc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBUtils {
	
	public static String SCHEMA_NAME = "public";
	
	// TODO - should we make them enum's
	// DB table names and columns
	public static String AUCTION_TABLE = "auction_item";
	public static String AUC_ID = "aid";
	public static String AUC_OWNER_ID = "auction_owner_uid";
	public static String AUC_TITLE = "auction_title";
	public static String AUC_CATEGORY = "auction_category";
	public static String AUC_IMAGE = "auction_image";
	public static String AUC_DESC = "auction_description";
	public static String AUC_POSTAGE = "auction_postage_details";
	public static String AUC_RESERVE = "auction_reserve_price";
	public static String AUC_BIDDING_START = "bidding_start_price";
	public static String AUC_BID_INCREMENT = "bidding_increment";
	public static String AUC_START = "auction_start_time";
	public static String AUC_CLOSE = "auction_close_time";
	public static String AUC_HALT = "auction_halt";
	
	public static String AUCTION_BID_TABLE = "auction_bid";
	public static String AUC_BID = "bid";
	public static String AUCBID_AUC_ID = "aid";
	public static String AUCBID_USER_ID = "uid";
	
	public static String USER_TABLE = "user";
	public static String USER_ID = "uid";
	public static String USER_NAME = "username";
	public static String USER_NICKNAME = "nickname";
	public static String USER_FIRST_NAME = "first_name";
	public static String USER_LAST_NAME = "last_name";
	public static String USER_PASSWORD = "password";
	public static String USER_EMAIL = "email";
	public static String USER_DOB = "year_of_birth";
	public static String USER_AVATAR = "avatar";
	public static String USER_ACTIVE = "activate";
	public static String USER_BAN = "ban";
	public static String USER_CREDIT_CARD_NUM = "credit_card_num";
	public static String USER_CHECKSUM = "activate_hashsum";
	public static String USER_IS_ADMIN = "is_admin";
	
	public static String ADMIN_TABLE = "user_admin";
	public static String ADMIN_UID = "uid";
	
	
	public static void main(String[] argv) {
		System.out.println(calculateMD5("password"));
	}
	
	public static String calculateMD5(String key) {
		String original = key;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(Integer.toHexString((int) (b & 0xff)));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
