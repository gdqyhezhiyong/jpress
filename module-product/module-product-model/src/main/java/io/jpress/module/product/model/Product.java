package io.jpress.module.product.model;

import com.jfinal.core.JFinal;
import io.jboot.db.annotation.Table;
import io.jboot.utils.StrUtil;
import io.jpress.JPressOptions;
import io.jpress.commons.utils.JsoupUtils;
import io.jpress.model.UserCart;
import io.jpress.module.product.model.base.BaseProduct;

/**
 * Generated by JPress.
 */
@Table(tableName = "product", primaryKey = "id")
public class Product extends BaseProduct<Product> {

    private static final long serialVersionUID = 1L;

    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_DRAFT = 2;
    public static final int STATUS_TRASH = 3;


    public boolean isNormal() {
        return getStatus() != null && getStatus() == STATUS_NORMAL;
    }

    public boolean isDraft() {
        return getStatus() != null && getStatus() == STATUS_DRAFT;
    }

    public boolean isTrash() {
        return getStatus() != null && getStatus() == STATUS_TRASH;
    }


    public String getUrl() {
        if (StrUtil.isBlank(getSlug())) {
            return JFinal.me().getContextPath() + "/product/" + getId() + JPressOptions.getAppUrlSuffix();
        } else {
            return JFinal.me().getContextPath() + "/product/" + getSlug() + JPressOptions.getAppUrlSuffix();
        }
    }

    public String getHtmlView() {
        return StrUtil.isBlank(getStyle()) ? "product.html" : "product_" + getStyle().trim() + ".html";
    }

    public String getText() {
        return StrUtil.escapeHtml(JsoupUtils.getText(getContent()));
    }


    public boolean isCommentEnable() {
        Boolean cs = getCommentStatus();
        return cs != null && cs == true;
    }


    public UserCart toUserCartItem(Long userId,Long distUserId){
        UserCart userCart = new UserCart();
        userCart.setUserId(userId);
        userCart.setDistUserId(distUserId);
        userCart.setSellerId(this.getUserId());
        userCart.setProductId(getId());
        userCart.setProductType("product");
        userCart.setProductTypeText("商品");
        userCart.setProductPrice(this.getPrice());
        userCart.setProductNewPrice(this.getPrice());
        userCart.setProductCount(1);
        userCart.setProductTitle(getTitle());
        userCart.setProductThumbnail(getThumbnail());
        userCart.setSelected(false);

        return userCart;
    }




}
