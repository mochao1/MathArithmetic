package com.demo.amt.operation;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Messi.Mo on 2019/6/24.
 * description: 文字样式
 */

public class TextStylesUtil {


    private static SpannableStringBuilder spannableString;
    private static TextStylesUtil textStylesUtil;
    private static String source;
    private static StringBuilder error;

    /**
     * 初始化
     *
     * @param sourceText 原文本
     * @return textStylesUtil
     */
    public static TextStylesUtil create(String sourceText) {
        source = sourceText;
        error = new StringBuilder();
        spannableString = new SpannableStringBuilder(sourceText);
        if (textStylesUtil == null) {
            textStylesUtil = new TextStylesUtil();
        }
        return textStylesUtil;
    }

    /**
     * 添加不能没有的目标文字
     *
     * @param target 目标文字
     */
    private void addError(String target) {
        if (!error.toString().contains(target)) {
            error.append(target).append(",");
        }
    }

    /**
     * 目标文字为空或为null时设置为全部文字
     *
     * @param target 目标文字
     * @return 新文字
     */
    private String setAllText(String target) {
        if (target == null || target.trim().isEmpty()) {
            target = source;
        }
        return target;
    }

    /**
     * 指定文字前景颜色
     * <p>
     * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE —— (a,b)
     * Spanned.SPAN_EXCLUSIVE_INCLUSIVE —— (a,b]
     * Spanned.SPAN_INCLUSIVE_EXCLUSIVE —— [a,b)
     * Spanned.SPAN_INCLUSIVE_INCLUSIVE —— [a,b]
     *
     * @param color  前景颜色
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setForegroundColorSpan(int color, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
            spannableString.setSpan(colorSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
                        spannableString.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else { //修改查询第一次出现的目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
                        spannableString.setSpan(foregroundColorSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }

        return textStylesUtil;
    }

    /**
     * 指定文字背景颜色
     *
     * @param color  背景颜色
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setBackgroundColorSpan(int color, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            BackgroundColorSpan colorSpan = new BackgroundColorSpan(color);
            spannableString.setSpan(colorSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        BackgroundColorSpan colorSpan = new BackgroundColorSpan(color);
                        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        BackgroundColorSpan colorSpan = new BackgroundColorSpan(color);
                        spannableString.setSpan(colorSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }

        return textStylesUtil;
    }

    /**
     * 指定文字字体
     *
     * @param type   字体( serif,monospace,sans-serif)
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setTypefaceSpan(String type, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            TypefaceSpan typefaceSpan = new TypefaceSpan(type);
            spannableString.setSpan(typefaceSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        TypefaceSpan typefaceSpan = new TypefaceSpan(type);
                        spannableString.setSpan(typefaceSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        TypefaceSpan typefaceSpan = new TypefaceSpan(type);
                        spannableString.setSpan(typefaceSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 文本对齐方式
     *
     * @param align  对齐方式
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setAlignmentSpan(Layout.Alignment align, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            AlignmentSpan.Standard alignmentSpan = new AlignmentSpan.Standard(align);
            spannableString.setSpan(alignmentSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        AlignmentSpan.Standard alignmentSpan = new AlignmentSpan.Standard(align);
                        spannableString.setSpan(alignmentSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        AlignmentSpan.Standard alignmentSpan = new AlignmentSpan.Standard(align);
                        spannableString.setSpan(alignmentSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 文本超链接
     *
     * @param target 目标文字
     * @param url    链接
     * @return textStylesUtil
     */
    public TextStylesUtil setURLSpan(String target, String url, TextView textView) {
        URLSpan typefaceSpan = new URLSpan(url);
        target = setAllText(target);
        int index = source.indexOf(target);
        if (index != -1) {
            spannableString.setSpan(typefaceSpan, index, index + target.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            addError(target);
        }
        return textStylesUtil;
    }

    /**
     * 指定文字绝对大小
     *
     * @param size   显示文字大小
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setAbsoluteSizeSpan(int size, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(size);
            spannableString.setSpan(absoluteSizeSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(size);
                        spannableString.setSpan(absoluteSizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(size);
                        spannableString.setSpan(absoluteSizeSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字相对大小
     *
     * @param size   显示文字大小
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setRelativeSizeSpan(float size, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(size);
            spannableString.setSpan(relativeSizeSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(size);
                        spannableString.setSpan(relativeSizeSpan1, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(size);
                        spannableString.setSpan(relativeSizeSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字基于X轴缩放
     *
     * @param size   显示文字大小
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setScaleXSpan(float size, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            ScaleXSpan scaleXSpan = new ScaleXSpan(size);
            spannableString.setSpan(scaleXSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        ScaleXSpan scaleXSpan1 = new ScaleXSpan(size);
                        spannableString.setSpan(scaleXSpan1, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        ScaleXSpan scaleXSpan = new ScaleXSpan(size);
                        spannableString.setSpan(scaleXSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字删除线
     *
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setStrikeThroughSpan(boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
            spannableString.setSpan(strikethroughSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                        spannableString.setSpan(strikethroughSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                        spannableString.setSpan(strikethroughSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字前竖线
     *
     * @param color  颜色
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setQuoteSpan(int color, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            QuoteSpan quoteSpan = new QuoteSpan(color);
            spannableString.setSpan(quoteSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        QuoteSpan quoteSpan = new QuoteSpan(color);
                        spannableString.setSpan(quoteSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        QuoteSpan quoteSpan = new QuoteSpan(color);
                        spannableString.setSpan(quoteSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }


    /**
     * 指定文字下划线
     *
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setUnderlineSpan(boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            UnderlineSpan underlineSpan = new UnderlineSpan();
            spannableString.setSpan(underlineSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        UnderlineSpan underlineSpan = new UnderlineSpan();
                        spannableString.setSpan(underlineSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        UnderlineSpan underlineSpan = new UnderlineSpan();
                        spannableString.setSpan(underlineSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字上标
     *
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setSuperscriptSpan(boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            SuperscriptSpan superscriptSpan = new SuperscriptSpan();
            spannableString.setSpan(superscriptSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
                        spannableString.setSpan(superscriptSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
                        spannableString.setSpan(superscriptSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字下标
     *
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setSubscriptSpan(boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            SubscriptSpan subscriptSpan = new SubscriptSpan();
            spannableString.setSpan(subscriptSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        SubscriptSpan subscriptSpan = new SubscriptSpan();
                        spannableString.setSpan(subscriptSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        SubscriptSpan subscriptSpan = new SubscriptSpan();
                        spannableString.setSpan(subscriptSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字风格（粗体，斜体等）
     *
     * @param type   风格
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setStyleSpan(int type, boolean isAll, String... target) {
        if (target == null || target.length == 0) { //全部文字
            StyleSpan styleSpan = new StyleSpan(type);
            spannableString.setSpan(styleSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        StyleSpan styleSpan = new StyleSpan(type);
                        spannableString.setSpan(styleSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        StyleSpan styleSpan = new StyleSpan(type);
                        spannableString.setSpan(styleSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字替换图片资源
     *
     * @param imageId 图片资源
     * @param isAll   是否查询全部
     * @param target  目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setImageSpan(Context context,int imageId, boolean isAll, String... target) {
        Drawable drawable = context.getResources().getDrawable(imageId);
        drawable.setBounds(0, 0, 42, 42);
        if (target == null || target.length == 0) { //全部文字
            ImageSpan imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(imageSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        ImageSpan imageSpan = new ImageSpan(drawable);
                        spannableString.setSpan(imageSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        ImageSpan imageSpan = new ImageSpan(drawable);
                        spannableString.setSpan(imageSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字点击事件
     *
     * @param target 目标文字
     * @return textStylesUtil
     */
    public TextStylesUtil setClickableSpan(String target, String show, TextView textView) {
        target = setAllText(target);
        int index = source.indexOf(target);
        if (index != -1) {
            MyClickableSpan clickableSpan = new MyClickableSpan(show);
            spannableString.setSpan(clickableSpan, index, index + target.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            addError(target);
        }
        return textStylesUtil;
    }

    /**
     * 指定文字修饰效果(模糊)
     *
     * @param radius 模糊半径
     * @param style  模糊样式
     * @param isAll  是否查询全部
     * @param target 目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setBlurMaskFilterSpan(float radius, BlurMaskFilter.Blur style, boolean isAll, String... target) {
        BlurMaskFilter filterINNER = new BlurMaskFilter(radius, style);
        if (target == null || target.length == 0) { //全部文字
            MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filterINNER);
            spannableString.setSpan(maskFilterSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filterINNER);
                        spannableString.setSpan(maskFilterSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filterINNER);
                        spannableString.setSpan(maskFilterSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    /**
     * 指定文字修饰效果(浮雕)
     *
     * @param direction  一个含有三个float元素的数组，对应x、y、z三个方向上的值；用于指定光源方向
     * @param ambient    环境光的因子 （0~1），0~1表示从暗到亮
     * @param specular   镜面反射系数，越接近0，反射光越强
     * @param blurRadius 模糊半径，值越大，模糊效果越明显
     * @param isAll      是否查询全部
     * @param target     目标文字集合
     * @return textStylesUtil
     */
    public TextStylesUtil setEmbossMaskFilterSpan(float[] direction, float ambient, float specular, float blurRadius, boolean isAll, String... target) {
        EmbossMaskFilter filterINNER = new EmbossMaskFilter(direction, ambient, specular, blurRadius);
        if (target == null || target.length == 0) { //全部文字
            MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filterINNER);
            spannableString.setSpan(maskFilterSpan, 0, source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            if (isAll) {  //修改查询的所有目标文字
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index == -1) {
                        addError(text);
                        continue;
                    }
                    Pattern p = Pattern.compile(text);
                    Matcher m = p.matcher(source);
                    while (m.find()) {
                        int start = m.start();
                        int end = m.end();
                        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filterINNER);
                        spannableString.setSpan(maskFilterSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

            } else {
                for (String text : target) {
                    int index = source.indexOf(text);
                    if (index != -1) {
                        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filterINNER);
                        spannableString.setSpan(maskFilterSpan, index, index + text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        addError(text);
                    }
                }
            }
        }
        return textStylesUtil;
    }

    public SpannableStringBuilder build() {
        if (error.length() > 0) {
            Log.d("textStylesUtil","没有找到文字:" + error.substring(0, error.length() - 1));
        }
        return spannableString;
    }

    /**
     * 设置文本字体
     *
     * @param path 字体资源路径
     * @return Typeface
     */
    public static void setTextTypeface(Context context,String path, TextView textView) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), path);
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        textView.setTypeface(typeface);
    }

    public class MyClickableSpan extends ClickableSpan {

        private String content;

        public MyClickableSpan(String content) {
            this.content = content;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {

        }
    }
}
