package com.hairui;

import com.hairui.util.DownloaderExcel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrawLers {
    private static String xpath;
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HaiRui\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/trending");
        xpath="/html/body/div[1]/header/div/div[2]/div[2]/a[1]";
        WebElement login = driver.findElement(By.xpath(xpath));
        login.click();
        driver.findElement(By.xpath("//*[@id=\"login_field\"]")).sendKeys(new GitHubUser().getUsername());
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(new GitHubUser().getPassword());
        driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[3]/input[8]")).click();

        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);
        List<Element> eleList = doc.getElementsByClass("Box-row");
        //System.out.println(eleList.size());
        List<Element> eleTop10 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            eleTop10.add(eleList.get(i));
        }

        Repositories repos = new Repositories();
        ArrayList<Repositories> reposList = new ArrayList<>();
        Elements eles1,elesName,elesLanguage,eles4;
        Element ele1;
        String str;
        int i = 1;
        for (Element ele : eleTop10){
            eles1 = ele.getElementsByClass("h3 lh-condensed");
            ele1 = eles1.get(0);
            elesName = ele1.getElementsByTag("a");
            str = elesName.get(0).text();
            String[] name = str.split(" ");
            repos.setUsername(name[0]);
            repos.setReposName(name[2]);
            elesLanguage = ele.getElementsByAttributeValue("itemprop","programmingLanguage");
            repos.setLanguage(elesLanguage.text());
            String star = ele.getElementsByClass("f6 text-gray mt-2").get(0).getElementsByClass(" muted-link d-inline-block mr-3").text();
            String[] stars = star.split(" ");
            repos.setStarsCount(stars[0]);
            xpath = "/html/body/div[4]/main/div[3]/div/div[2]/article["+i+"]/h1/a";
            i++;
            driver.findElement(By.xpath(xpath)).click();
            String html1 = driver.getPageSource();
            Document doc1 = Jsoup.parse(html1);
            String  branchesCount = doc1.getElementsByClass("num text-emphasized").text();
            String[] branches = branchesCount.split(" ");
            repos.setBranchesCount(branches[1]);
            System.out.println(repos);
            xpath = "//*[@id=\"js-repo-pjax-container\"]/div[1]/div/ul/li[1]/form/details";

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border = \"5px solid yellow\"",element);

            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(10000);
            xpath = "//*[@id=\"js-repo-pjax-container\"]/div[1]/div/ul/li[1]/form/details/details-menu/div[2]/button[3]";

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement element1 = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border = \"5px solid yellow\"",element1);

            driver.findElement(By.xpath(xpath)).click();
            reposList.add(repos);
            driver.navigate().back();
        }

        for (Repositories r : reposList){
            System.out.println(r);
        }

        DownloaderExcel excle = new DownloaderExcel();
        excle.excel(reposList);
        driver.close();
    }
}
