package com.kienlt.CashBack.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kienlt.CashBack.entity.CashBack;
import com.kienlt.CashBack.repository.ICashBackRepository;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

public class GetCashBack {

    private static String PATH_PROJECT = System.getProperty("user.dir");
    ICashBackRepository cashBackRepository;

    public GetCashBack(ICashBackRepository cashBackRepository) {
        this.cashBackRepository = cashBackRepository;
    }

    public List<CashBack> HandleHTML() throws Exception {
        List<CashBack> lstCashBack = new ArrayList<CashBack>();
        File myObj = new File(PATH_PROJECT + "/AI.txt");
        Scanner myReader = new Scanner(myObj);
        Calendar c = Calendar.getInstance();
        int i = 0;
        CashBack cashBack = null;
        // 0 4 6 7
        while (myReader.hasNextLine()) {

            final String data = myReader.nextLine();
            if (getTagValues(data).toArray().length > 0) {

                String value = getTagValues(data).toArray()[0].toString();
                i++;
                if (i - 1 == 0) {
                    cashBack = new CashBack();
                    cashBack.setDateOfSale(new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(value));
                } else if (i - 1 == 4) {
                    cashBack.setMyCashBack(Double.parseDouble(value.replace(" $", "")));
                } else if (i - 1 == 6) {
                    Integer await = Integer.parseInt(value.replace("≈", "").replace(" days", ""));
                    cashBack.setAwait(await);
                    c.setTime(cashBack.getDateOfSale());
                    c.add(Calendar.DATE, await);
                    cashBack.setDateOfApprove(c.getTime());
                } else if (i - 1 == 7) {
                    cashBack.setStatus(value);
                    lstCashBack.add(cashBack);
                }
                if (value.equals("Pending") || value.equals("Approved")) {
                    i = 0;
                }
            }
        }
        myReader.close();

        if (lstCashBack.size() > 0) {
            for (CashBack cb : lstCashBack) {
                CashBack cashBack_ = cashBackRepository.findByDateOfSaleAndMyCashBackAndAwaitAndStatus(cb.getDateOfSale(),cb.getMyCashBack(),
                        cb.getAwait(),cb.getStatus());
                if (cashBack_ == null)
                    cashBackRepository.save(cb);
            }
            return lstCashBack;
        }
        return null;
    }

    private static final Pattern TAG_REGEX = Pattern.compile("<td data-v-794b9fd0=\"\">(.+?)</td>", Pattern.DOTALL);

    private static List<String> getTagValues(final String str) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    public static Integer formatHTML(String html) {
        try {
            FileWriter myWriter = new FileWriter(PATH_PROJECT + "/AI.txt");
            org.jsoup.nodes.Document doc = Jsoup.parse(html, "", Parser.xmlParser());
            myWriter.write(doc.toString());
            myWriter.close();

            return 1;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return 0;
    }
}
