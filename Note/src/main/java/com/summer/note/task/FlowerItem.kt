package com.summer.note.task

data class FlowerItem(
    val imageSrc: String,
    val imgHeader: String,
    val name: String,
    val address: String,
    val brief: String,
    val starNum: String
) {
    companion object {
        fun getTestData(): MutableList<FlowerItem> {
            return mutableListOf(
                FlowerItem(
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.163ny.com%2Fd%2Ffile%2Fbigpic%2F051720%2F20170119143756_99772.jpg&refer=http%3A%2F%2Fwww.163ny.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1637419210&t=f0788ad5747e4a057f5ae5403d19e94a",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202008%2F08%2F20200808184819_FZdWn.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1637420169&t=063ffd9397dce40e5e4443a0823cadd0",
                    "我就是取这么长的名字你能奈我何",
                    "位置：成都",
                    "鹤望兰（Strelitzia reginae Aiton）芭蕉科鹤望兰亚科 [1]  多年生草本植物，无茎。叶片长圆状披针形，长25-45cm，宽10cm。",
                    "20"
                ),
                FlowerItem(
                    "https://img2.baidu.com/it/u=3678859753,2654025409&fm=26&fmt=auto",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202008%2F08%2F20200808184819_FZdWn.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1637420169&t=063ffd9397dce40e5e4443a0823cadd0",
                    "罪",
                    "位置：北京",
                    "蜡梅（拉丁学名： Chimonanthus praecox (Linn.) Link （《Flora of China》），英文名：Winter Sweet，中文别名：金梅、腊梅、蜡花、黄梅花。",
                    "255"
                ),
                FlowerItem(
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1405%2F19%2Fc26%2F34393779_1400486070903.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1637501372&t=b3c0fcf2c344fabfc18a7768d8420260",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202008%2F08%2F20200808184819_FZdWn.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1637420169&t=063ffd9397dce40e5e4443a0823cadd0",
                    "浮生",
                    "位置：广州",
                    "木芙蓉为落叶灌木或小乔木，高1~3m。枝条较密并生有星状毛。叶为互生，呈阔卵圆形或圆卵形，掌状3~5浅裂，先端尖或渐尖，两面有星状绒毛。",
                    "22"
                )
            )
        }
    }
}
