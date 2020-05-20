package com.linhuanjie.guava.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 *
 * @author : linhuanjie
 * @createtime : 2020/5/20 11:57
 **/
public class BloomFilterDemo {

    public static void main(String[] args) {

        /**
         * 创建字符串布隆过滤器，使用编码UTF-8
         *
         * Funnel，这是Guava中定义的一个接口，它和PrimitiveSink配套使用，主要是把任意类型的数据转化成Java基本数据类型（primitive value，如char，byte，int……），默认用java.nio.ByteBuffer实现，最终均转化为byte数组
         * expectedInsertions 期望插入数据数，int或long
         * fpp期望误判率，比如1E-7（千万分之一）
         * Strategy 策略，默认选取64位哈希映射函数，BloomFilterStrategies.MURMUR128_MITZ_64
         */
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);

        // 置入元素
        bloomFilter.put("测试测试");

        // 判断元素是否存在，true存在，false不存在。
        boolean isContain = bloomFilter.mightContain("测试测试");
        boolean isContain2 = bloomFilter.mightContain("测试测试222");

        System.out.println(isContain);
        System.out.println(isContain2);
    }

}
