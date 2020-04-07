/** Alipay.com Inc. Copyright (c) 2004-2017 All Rights Reserved. */
package biz.acts.test.articlemange.saveorupatearticle;

import com.alipay.sakd.SOFABootTestApplication;
import com.alipay.test.acts.annotation.TestBean;
import com.alipay.test.acts.model.PrepareData;
import com.alipay.test.acts.runtime.ActsRuntimeContext;
import com.alipay.test.acts.template.ActsTestBase;
import com.soft.sakd.biz.mange.ArticleMange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @author xujie
 * @version $Id: SaveOrUpateArticleActsTest.java,v 0.1 2020-04-05 上午 09:54:51 Administrator Exp $
 */
@SpringBootTest(classes = SOFABootTestApplication.class)
public class SaveOrUpateArticleActsTest extends ActsTestBase {

  @TestBean @Autowired protected ArticleMange articleMange;

  /**
   * 说明：runTest(caseId, desc, prepareData)脚本中的process方法中的clear,prepare,execute,check四个方法如果无法满足脚本
   * 需求可以进行重写。forExample: public void prepare(ActsRuntimeContext actsRuntimeContext) throws
   * ActsTestException { userDifined();//自定义数据准备; super.prepare(actsRuntimeContext);//继承父类数据准备方法 }
   *
   * <p>{@link
   * com.soft.sakd.biz.mange.ArticleMange#saveOrUpateArticle(com.soft.sakd.biz.param.ArticleParam)}
   */
  @Test(dataProvider = "ActsDataProvider")
  public void saveOrUpateArticle(String caseId, String desc, PrepareData prepareData) {
    runTest(caseId, prepareData);
  }

  //  --clear --prepare --execute --check

  @Override
  public void beforeActsTest(ActsRuntimeContext actsRuntimeContext) {
    //    super.beforeActsTest(actsRuntimeContext);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("文本参数不为空");
    actsRuntimeContext.setException(illegalArgumentException);
  }

  public void setArticleMange(ArticleMange articleMange) {
    this.articleMange = articleMange;
  }
}
