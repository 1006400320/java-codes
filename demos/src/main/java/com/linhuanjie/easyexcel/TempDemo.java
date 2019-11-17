package com.linhuanjie.easyexcel;

import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2019.09.07 17:35
 * @email: lhuanjie@qq.com
 */
public class TempDemo {

    public static void main(String[] args) {
//        String tempImgs = "788019a5a69149cfea20eed11e13b228.jpg,c33c1e97a28b31dd23952bf641d45aef.jpg,bdd1b1f3f13ee19a4868e05162b681d6.jpg,a5020490b04445e9c5ec037d5051dd14.jpg,56c51d5fbc62f314133197e6efca55ae.jpg,d8eba18474622255b97bfddd817fa66e.jpg,76ad9c7d8b967755a59c0f0a7f8201d5.jpg,227d5a85d07a2ebc139ce2143bfe9161.jpg,73d84702632a430442f69ce5f5596eeb.jpg,b9f741bdfd876c6ba660e0bcbf0fc631.jpg,3693ba84464c69f242b864838d7f2fc1.jpg,f8383f50dc59c998b5c01499786139bc.jpg,dcefb4b02463a0d362fbf414067bc4a1.jpg,a8e470288c4d0c2129be126c395cf3a0.jpg,aad2fcce92ae84d97e196455fe088f8d.jpg,daa543e2918d773dfa51fc269a66e492.jpg,2574e522bf53048cea760efa16b0cd8b.jpg,88b247ed215d0c8a7b43b62e7ab1b7fe.jpg,2635005875d259e4982e070c89192624.jpg,5b7053a3429f3d76e3f44dbe790fa562.jpg,e049fe40aca313f38580ddd66bb269e2.jpg,d57fc4fa09fb555da2edf59a3923443e.jpg,2b8207b799b91524e681f7b3fbdf808e.jpg,01c8d7cd2d83652319840fc9a74c1985.jpg,a433dcd3f359611ffce58708328f9b30.jpg,69176a815f4769f108a7c4f3c75546b2.jpg,440882aeb927dc1e942dcc73e5f6a3f7.jpg,ad06c6ee78da4f4867d273b6ce8b73f8.jpg,42c662b2425a42d61530a4463175208f.jpg,d2952268e310f13f0f34e58093651f99.jpg,302b8d0899641d7f0e532ccccede64c9.jpg,9fdbe7b9278c7cc93d1eed30639e47a6.jpg,108714b417a6031ba407d1d281c39cb5.jpg,e3d6674c2b3e3363c711409feb6f082a.jpg,df1036180a9300924b1e959cea38ed7c.jpg,4440c484b3bf08323bacf921a8ee7bcb.jpg,fba7227b5619c0b25b5ca6bae7fd9c04.jpg,5d9b13a663bd70d1e2b19e227e9a7674.jpg,a79e79b0a26f4c815c7352370c40632c.jpg,7f1b0c0edd838a7c17f37f6a0ad8a134.jpg,84ea97eec19eb52e5a6e903520341901.jpg,d28344efcc8f3b2c1247ba94cf3a9e42.jpg,c1a937e513433f87fb504c435d7adfd5.jpg,12e5dd3475c5057b7cfd0e7c3d831cfe.jpg,302db4f96856e671028759cd053c48e5.jpg,c21e3083c34ce9c31bd902b06932c37d.jpg,52bac6e8dfde9e0d14662a86df5dc6a2.jpg,35959f72b40838e8d0a676b7164b4c46.jpg,18913b30e97377256fa015ae0bcb1edf.jpg,abc808ed93211bd7a5ba6a4597f2b984.jpg,8d8bc166e283ad464d814f0bbe55d1fe.jpg,f1d3fee1389397afe0da9b6c7e211a60.jpg,68b2598ad41c8e46e8cfdf12786a6846.jpg,33acff8aa0770c1e532ca8ae3e4f1172.jpg,42f4fa5ef09ef17f1ec44c8c817e332a.jpg,44ccc8c17088c6d55ef743670e35da74.jpg,1093191a06e5772a9988cb347a7d4375.jpg,217dc8d99e69d3d4c609bda6a5437bd9.jpg,b4f7b8e5d2b9e6154e076a58250c032e.jpg,1dde86a48dd1f160fc903f06384a1067.jpg,6523befe9773345c30d5077f16202c75.jpg,e423245a0f472e5624aae0ab8d76cbcb.jpg,e46ceb96a6a5eb9f89600188ed743927.jpg,bb8ecb6dc2c5145cfd495bb6726095cf.jpg,9d364b3429d502e51319d37cbe96d1b6.jpg,b28c505a0300a9a7c66029a4ab019cf9.jpg,05615353dfbe13eb929144c2d8ba41a2.jpg,d26c9b6314070ea527a18e27777c29f4.jpg,e7b08716b78a422b2f31b733080d8d77.jpg,bc947384ced2a6254ba476dc944f6b7c.jpg,ebb2121cd7611f9e8d7bc46a1fdb28a7.jpg,1b4bb844950abcb3aa67bd8bc2813478.jpg,74e62044a95277a572ecf81f46194108.jpg,6e34da5abab4a2048b9f09a5ff5b93f3.jpg,0c7f7b54fb20d1b05403c856916727a6.jpg,4e8f92bc4e583e805683508761914cda.jpg,e5a6173572b781b789e24f31508da641.jpg,1e39b791d94851a1c4e80bbbfa2f947b.jpg,22566f5786948809c7557224e5614258.jpg,9c4b275c85b10fbffa39273acb7f594f.jpg,22862b8fdefe5d3bf568ea26821dc931.jpg,4b0a2053303cd7d4fc3e9a74a2bce0bd.jpg,74919c96e2977995cff398d79fab986d.jpg,6aafad04944ad6a8a29ab172be8a76ee.jpg,eb38d7795b6873df2fd8a251d09f00c3.jpg,248e2f29dce205e82f906923f0389c5f.jpg,0de06702be20edb25513042d63d805e7.jpg,4cc18c59b2606979ca268f381bc9a103.jpg,44b78807fbab23ca5773ba1676fdfad4.jpg,37bd2db55c29f2056d3703d43a1ca194.jpg,3ea87f5d844317e6e64c480e2760c3c9.jpg,6dcc23de6da8ac704f8d33f91dfbc056.jpg,772b757383a3e875b84ab9ffdb9ae1dd.jpg,21949ee6cc2e372b0fa28c9fb3202745.jpg,9405a1d705ce8ac891f995f183152946.jpg,e28495981136d431a1cac246c2142922.jpg,c5dbe7e0347164a2d9d2e2931111b720.jpg,3075f93fd63bce3565e71b29db4e9fcd.jpg,a9b33ccd4ffe2f08c775adc8970cedeb.jpg,08c252122004623ba6582fbd0c47bb54.jpg,ee1743cffa0de101d6c7b18f8f79947b.jpg,08800ec5f36f3576e1065c920938ca5b.jpg,796c11518513ef966f6fd9b4d8d19faa.jpg,8160b851ad6ef694560916cc2a6bf8e3.jpg,1cf07ad99365b55f85812f71a24444ed.jpg,4db39806a203d69b510b432a90d90ad4.jpg,884c7f29da736d071d4c688181316f25.jpg,8ad1ba52cb11f7e7a659860345d6fd9d.jpg,b15280f469bba24c86d581efdfd81ea9.jpg,54f2c3f065293a8e7e556869f8e4cc23.jpg,5b0b6644f681a438f1c1b7cba63cf616.jpg,8adac74cfc827ff638b7f11880949509.jpg,ad32b43a502b630691ab8b086962642a.jpg,76f443a2a928ec91746bdba33e67b573.jpg,51a1db53d5fc9a71bc1f9e49e23ab89a.jpg,beebc35181bf1d11c9ae9e95f7daa963.jpg,c97ab2e69175558018fc8ff2540ed7d7.jpg,ccf8d075ea8d6045157d87787df17f4f.jpg,8c10ca3e5b9bcebbe828256e7029feab.jpg,f003724813885384697aeda3152ab94c.jpg,e8434aa31280ad8afe97d705d022de1c.jpg,bfea24b3184457a7e64017af520aa60e.jpg,e23c569772203413871aa4a75c7aea85.jpg,9317e244879fff9d197b3fe54e57305e.jpg,9545dd51848113fef53072e43be119b3.jpg,b65b9837ce2f1c4e10eef9cc1302066e.jpg,3671ae51644d1f3e66e2a9bb5bbd65fa.jpg,4b1abd17f63a83dbd358d71b4384aadf.jpg,e080ee67228c95d6f22297aa4033f379.jpg,aca06f0f8489953189bcd99c83613d5d.jpg,1f5669ad13bb8e9dc03871e6aa57c2d2.jpg,63666318e185d040f83058c4a9202ce3.jpg,f891c7a49807c51317dcf16ffc361f80.jpg,f7ef26998d669608fc83225b1b5c0784.jpg,8725df9a17f3311bcea8af7e185e47ed.jpg,0e98c6f6349979169061cb1aa7b8128e.jpg,87ccb24a8e76e99215b0cbd0726e351d.jpg,63ecdc60ba102a164955999a0d1dbbc7.jpg,b1d33495f88057dc98bf1c1bff98e213.jpg,a4b02a53bed90fbd58c0acefb15cf291.jpg,3cdd7fb8fdfb7fb114c7cb992dab0291.jpg,7a8043424715d8a72aab4b441fd9e758.jpg,b01c79f736088ccd1ae3ada7418c6f35.jpg,4fb446df657ed2bf196088a31eec3c7a.jpg,c5b6c79b14e276d5a9fac789ca10f142.jpg,e91035b0ad39d0e9ee547d77a7584d08.jpg,763271a68a4f39ad291b6b669b902365.jpg,c9f1985aed3dd4cd20478e05e0f6762b.jpg,4b5db57acde9adc9175bfc09a340ec3d.jpg,41fb4e950d63a46b626db3b4669425e5.jpg,6ac148c676732a1937417e5fcd0b7d9a.jpg,7837481628ccf18259212d9313ebd418.jpg,f7d742df0f5108c6b24480af28cf81b6.jpg,abecf806645090c5575a54be0feec24f.jpg,6e3bc0107a4189688ddc5d2639f0c91d.jpg,07d0ca5328614d953d58ec20a9a73c5e.jpg,24e149091228add0fb910bf4041153fc.jpg,1330f5fe2e536511289c607ddbcb5f2d.jpg,900e288c714a50d5f9e422bc9b455d66.jpg,2311de17a431efd09a2c0fd7a5e031e6.jpg,5f8848c283f017cf44ec3b95d3ba56f9.jpg,0c73b9f2043d08e98e02d52fd5b94ba9.jpg,3998bc887561f170258d52acbdfe51f9.jpg,ca0d7f54329d717dd732d250dd4e5b23.jpg,b8a6e6033f616bee8c7f3a75825e2c0c.jpg,c79f36ad4b7ed1bf868f09a17ab4eaf0.jpg,40fec3e6aa68369fab6c24a26bb02ea4.jpg,850a7521451fd75be8b50def42c3beb2.jpg,c9582a43a1322c3b6d968bb7340c4b76.jpg,5df6e03e31b105c696712373bf8c36f6.jpg,9107600d090e22bbc3078ba2c25caa5e.jpg,6f372570870497c44fd5077911ca71ef.jpg,31a20e5075435198f2189a22cb23a55f.jpg,fd8a53ff36b75174aa325e5cee8e1f9c.jpg,3bb54bbdc03c5187fcdaa594c2fba063.jpg,465277661011155513c2267647021a33.jpg,86db684a5e0036cf441d2c875e0adbed.jpg,7212f2d7c5752ac637fda477970c0ea7.jpg,9fb39c171fc3e8c4bac68c53c386f628.jpg,7efcf97f729ab4417889ddb1a2c99258.jpg,c52268afc81798a74f3020e8c5a3fb36.jpg,b2dd61304728008d67518c53097fc8aa.jpg,03add08c7970f3f74c61556547e05d99.jpg,88f9d3cfa0b27a45b20cd4087fc02a4d.jpg,1a05867ea3af13b30a3c981d45a8d8a5.jpg,389896abd77d1f7660d968e6b3cfcfb1.jpg,51bacf3252c0e8921b72ee4daa83ba2e.jpg,f7ace56bcb1b38669a88233f765648da.jpg,2e5d4a0b26fe1d6bd7da5ad5e139580a.jpg,560b27da385a39c71847c72beb419513.jpg,108202f7a57d509d000cf7d49805fa89.jpg,343e64d593f1f357e033d83e9f6747f9.jpg,c2f4a45338fd745956401135f91c451b.jpg,e14b8d375c49a73e6d482774d31ce58d.jpg,acd363fa30bba83d4b0d97ed94cfec23.jpg,9cfc8c378887f130c0ff80abfd3cb5ec.jpg,18983ec2ece42330a26937645916a9da.jpg,753b2c767a7619a0760db991d9b1eeb4.jpg,b8b11a2a38b7bcfd18b19495f5675f7b.jpg,4f14f0919fe40300af90e81b88afcd48.jpg,96b88936f7adbf515538e55e17b585fc.jpg,9249bdf6c3327a914b78db42cf73cdfd.jpg,65ccbad5ba4ed10e004fb081dfaa9077.jpg,9a6e33a207f143c896f7dd2fc6ebbe4a.jpg,57280db860d1ff2796f39c7362818fe0.jpg,c18c9e9d7bbc85c9a5be9e31323607ff.jpg,6d27b64772315791ec06cb2b515a3d1d.jpg,e0b08700acbd7524c486e9cabe4b621a.jpg,3eda0eb9fcd69e422909854de7c7c480.JPG,9ad736e35e171e87edb5a67bf741b41e.jpg,bfe11f50cf220bf95ad5dab7def4b72b.JPG,58cc95913eaa0eea462585a279547ff5.jpg,76a95effdb1b87c16918465be563e19d.jpg,2badf0e64baf06702d5485bc6afa5054.jpg,de2a2631285f3e4e347f8f5b98819ef6.jpg,7f0cb4f63f8e4f707c06641de6fc257c.jpg,246f19e22af655a31173759d72dad104.jpg,87c5f5709d4c9a9456a343ec38d1b745.jpg,50e274b88b65cdb120756ceede801244.jpg,87cec7117ad7f6a2b76305320c825204.jpg,cdfa743ca932b95cf9217dfc823a8b41.jpg,0a01ba37e93204782867d9c65f0c04ca.jpg,e1e1b9e9575d8f61fd2313b9b4d4ec4f.jpg,f3f8dfd836fa36e3e04ecaccb031ec19.jpg,c9f943368c40da0ea8e5458ed2eb8982.jpg,6a022c8b209f3501bbcdcafbb7e1c75b.jpg,8835f661a6fbef19132639c6c4e29e9b.jpg,af99b120e89e88e5f7240467e55dd08a.JPG,bd48e3bfc670092b62924afbc3c39120.jpg,722aaa535fcf72985704ef009639cb3b.jpg,f66ba9178d5f4ed72855b75dd8edd6dc.JPG,a9f96a8119698f854ae44506b16550f0.jpg,1160cdeeaf07675078dcc178fc088bf2.jpg,0cf59561ff394d7d718bae84a3147586.jpg,8a476f2f105ff8c22a2face07cf6c157.jpg,41981298869d34d8a64bb4cb9f2dbb45.jpg,e823c2204e566137430e40bd4b7b75c0.jpg,c0169e02ee605c604236966228d605ec.JPG,a670ad0979f57bc6f22147e04a0efa24.jpg,33ab694aef9ed1cc345ff503f582d0d2.jpg,e8766dc05b369c2933fbade0451c89a8.jpg,c21ea9fd0a87dec2527e4fe30d870563.jpg,f350469988adb4ef7ca2ecd9d03d5af1.jpg,57c4df6df62de9c12e4720f14a9bccae.jpg,e74794c23e3527d9d1a35666ae13a3ae.jpg,d4345d9820579abd2457b3912507aaa6.jpg,2e2917dc892752adfaad56a8f3658a54.jpg,529c3e104e06aabb8a9eef164190c379.jpg,28836e3150e7438b11e472ba5bd9bbac.jpg,9e750821a22bcd292622478306187356.jpg,46af7613d0c216bff8d40ec1bb66fcc8.jpg,7052d6278fd98cf764e0ff8731302ba3.jpg,c3d8233b1a0bf33021b769041b7c3f63.jpg,b5e3384a70030ae7953ebe113745138c.jpg,bb90756ec990f0b3cb4913ecd2e3bf35.jpg,fdafbe4e1e12eb5b07be23b18ae5caed.jpg,12b96d803ea8097b8b308617dcc84555.jpg,51d358b3ea5a51e013099495536ad208.jpg,7fd2d10a96ce9856c65f0811fd7ebd54.jpg,114b41f2917d2efb764f1d05913537a4.jpg,36c0d140d34c724f312bb7fa96da5c26.jpg,3ba118b5b1bc6c44f34dc604c0f23c29.jpg,988404ee67fe20eeb9608f587fac32bd.jpg,502dba35357045dcdad2cd717d10e862.jpg,16b9f232f3abd25ef112eb1a08269a7e.jpg,d080603d730ceb060419dfbdca63eaa1.jpg,320f60b9debfac9674dd052b5013f592.jpg,ce3710340125c3b9ea526aa6492c1819.jpg,817313f7c1d92f5e7293ef4479b34f76.jpg,2853fb5cb2bd8bc4ede152ffabd3525c.jpg,6aca63d214bc0694b8637b6da75615a5.jpg,a88a9f40d8ecc676c1bdf4a5bd0ddf56.jpg,5d4fae2aee5397c428a7b406f397449d.jpg,5f5fbea8a9a57b39eac0593e5354662b.jpg,228cedeca3b99e8a7345f914d4e999f3.jpg,6ebf68718aab1026c0b6d1634e8bae28.jpg,34acd70b2a424a48c12c90541062f205.jpg,3acf609ccf1dc9ed633745d29e517725.jpg,deaa94a6ed920284d47e664c4360b8df.jpg,c48de5cba62c9648c84eb98001dfde9b.jpg,10b66a77c96b819e856a0ff9c2a050ca.jpg,8d56b605d4aff3e266ab02df81de5f0f.jpg,2666d8cbee4285c5504ac7174d0be4a4.jpg,4f750412c68b67230a482ea523333d3f.jpg,7d59e9b61b1e49ff406ce69136cb8967.jpg,a733b3461ae3818f679811b5bc58a856.jpg,daebd837a4d0535f93566f2aaf6e5bfc.jpg,b4a5386d28c3bdce1861f08ceeb1d8bd.jpg,4bd7e022319ac19e0858e804402f382e.jpg,a41f8c9c9246f279eeb2f21e01960f97.jpg,fd45242b62527982a72c948026e2f487.jpg,4fe0354521eee7036bab9097fe2d08a3.jpg,38f11169f0168513d81f7b68f5d59a8e.jpg,024dc73fa9ccb899945913930fa24504.jpg,db232828652c15b6c934bd8d81088248.jpg,70eb8f580a3e7a5e4adcafccc28046ee.jpg,cc61b02eda556b1d658d12d0cf7e8407.jpg,268043c3895208dd997b12e563ffe783.jpg,2ab08ed6efe1492081f819d0bba902fd.jpg,6b4b4040296f2e9b21a78a5c60794956.jpg,b3d9cbe43790e8b4068d02be3b4f4b08.jpg,0596416ca869b29c493c16cc76f80724.jpg,c893125ec27ec2b8f6032d19f15c14d5.jpg,ce5138af37e804f0a212cd1c2b1396f6.jpg,fb24d86f8b2f33ca2a2ca44617ab79ce.jpg,0e8f07ca9383c9231a17c38eaed4be51.jpg,b6395ec54c8290be9022c37b432a1840.jpg,5dc6b2773bcb88cc19acf10e47da54dd.jpg,d2a172a9f98e9d849d4d265b04cabf30.jpg,c68e028455ffec569e3cad25a77c2f05.jpg,85dea5881f19664c5cfd518c5f781b69.jpg,436d646d706a23767acf6dfadc46c49d.jpg,cb31739c1fd9ed61c5d04f547f584236.jpg,aba5d42df2b7323c950812ca1f04565a.jpg";
//        delImg(tempImgs);


        ExecutorService fixedThreadPool  = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;

            fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread() + ":" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void delImg(String tempImgs) {
        Long start = System.currentTimeMillis();
        List<String> successDel = new ArrayList<>();
        List<String> failDel = new ArrayList<>();
        String[] imgs = tempImgs.split(",");
        for (String img : imgs) {
            if (deleteImg(img)) {
                successDel.add(img);
            } else {
                failDel.add(img);
            }
        }
        System.out.println("successDel:" + StringUtils.join(successDel, ",")+ "共" + imgs.length + "条，耗时："+ (System.currentTimeMillis()-start) + "ms");
        System.out.println("failDel:" + StringUtils.join(failDel, ","));
    }


    // 删除七牛上的图片
    public static Boolean deleteImg(String imgUrl) {


        Boolean result = false;
        Auth auth = Auth.create("aXn3u9xg6r_9qNAtY2l1LHPuAgVyspSX19Q093Rx", "5SVa1yw2gHygBRni-D-x8UrsuKskOa5qFMIneDXS");

        String accessToken = auth.uploadToken("woniutrip");
        BucketManager bucketMgr = new BucketManager(auth);
        //指定需要删除的文件，和文件所在的存储空间
        String bucketName = "woniutrip";
        try {
            bucketMgr.delete(bucketName, imgUrl);
            result = true;
        } catch (Exception e) {

            try {
                if (StringUtils.isNotBlank(e.getMessage()) && !e.getMessage().contains("QiniuException")) {
                    System.out.println("删除七牛图片失败：imgUrl:{}" + imgUrl + e.getMessage() + e);
                }
            } catch (Exception ex) {
                System.out.println("删除七牛图片失败2：imgUrl:{}" + imgUrl + e.getMessage() + e);
            }
        }
        return result;
    }
}


