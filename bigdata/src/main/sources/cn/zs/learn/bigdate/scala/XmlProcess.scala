package cn.zs.learn.bigdate.scala

object XmlProcess {
    def main(args: Array[String]): Unit = {
//    val str = "[haha,hehe]"
//    println(str.substring(1, str.lastIndexOf("]")))
//    println(str.substring(1, str.length()-1))
//    val a1 = Array(1,2,3,4,5,6,7,8,9,10)
//    val a2 = Array(7,8,9,11,12,13)
//    println(Set(a1:_*) -- Set(a2:_*).toArray)
//    
//    println(StringUtils.isNotBlank("ss"))
    
    val elme = <dependency><groupId>junit</groupId><artifactId>junit</artifactId><version>3.8.1</version><scope>test</scope></dependency>
//    println(elme.toString())
//    val attr = elme.attributes
//    println(attr)
//    println(elme.child)
//    println(elme.label)
//    println(elme.text)
//    println(elme.doTransform)
    
//    val file = Source.fromFile(new File(ScalaTest.getClass.getClassLoader.getResource("hadoop/hdfs-site.xml").getFile), 4096)
//   for(c <- file) print(c)
//   
//   val pom = Source.fromFile(new File(System.getProperty("user.dir")+"/pom.xml"))
//    for(c <- pom) print(c)
    
//    val pom = XML.loadFile(new File(System.getProperty("user.dir")+"/pom.xml"))
//    println(pom.toString())
//    println(pom.label)
    
//    val item= Array(2,5)
//    val xml = <ul>hh<li>{item(0)}</li><li>{item(1)}</li></ul>
//    for(nodes <- xml.child){
////        println(nodes.isInstanceOf[Text])
//      println(nodes.isInstanceOf[PCData])
//      println(nodes.isInstanceOf[Unparsed])
//      println(nodes.isInstanceOf[Text])
//      println(nodes.isAtom)
//        println("*****")
//      for(node <- nodes.child){
////        println(node.isAtom)
////        println(node.isInstanceOf[Text])
//        println(node.isInstanceOf[PCData])
//        println(node.isInstanceOf[Unparsed])
//        println(node.isInstanceOf[Text])
//        println(node.isAtom)
//      }
//    }
//    println(xml)
    
    val medline = <MedlineCitation Owner="PIP" Status="MEDLINE">
<PMID Version="1">12255379</PMID>
<DateCreated>
<Year>1980</Year>
<Month>01</Month>
<Day>03</Day>
</DateCreated>
<DateCompleted>
<Year>1980</Year>
<Month>01</Month>
<Day>03</Day>
</DateCompleted>
<DateRevised>
<Year>2013</Year>
<Month>02</Month>
<Day>19</Day>
</DateRevised>
<Article PubModel="Print">
<Journal>
<ISSN IssnType="Print">0002-9955</ISSN>
<JournalIssue CitedMedium="Print">
<Volume>159</Volume>
<Issue>3</Issue>
<PubDate>
<Year>1955</Year>
<Month>Sep</Month>
<Day>17</Day>
</PubDate>
</JournalIssue>
<Title>Journal of the American Medical Association</Title>
<ISOAbbreviation>J Am Med Assoc</ISOAbbreviation>
</Journal>
<ArticleTitle>Association of maternal and fetal factors with development of mental deficiency.  1. Abnormalities in the prenatal and paranatal periods.</ArticleTitle>
<Pagination>
<MedlinePgn>155-60</MedlinePgn>
</Pagination>
<AuthorList CompleteYN="Y">
<Author ValidYN="Y">
<LastName>Pasamanick</LastName>
<ForeName>B</ForeName>
<Initials>B</Initials>
</Author>
<Author ValidYN="Y">
<LastName>Lilienfeld</LastName>
<ForeName>A M</ForeName>
<Initials>AM</Initials>
</Author>
</AuthorList>
<Language>eng</Language>
<PublicationTypeList>
<PublicationType UI="D003160">Comparative Study</PublicationType>
<PublicationType UI="D016428">Journal Article</PublicationType>
</PublicationTypeList>
</Article>
<MedlineJournalInfo>
<Country>United States</Country>
<MedlineTA>J Am Med Assoc</MedlineTA>
<NlmUniqueID>7507176</NlmUniqueID>
<ISSNLinking>0002-9955</ISSNLinking>
</MedlineJournalInfo>
<CitationSubset>J</CitationSubset>
<MeshHeadingList>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D001519">Behavior</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D000013">Congenital Abnormalities</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D006233">Disabled Persons</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D004194">Disease</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="Y" UI="D008607">Intellectual Disability</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D007360">Intelligence</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="Y" UI="D008431">Maternal-Fetal Exchange</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D010551">Personality</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D011247">Pregnancy</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="Y" UI="D011248">Pregnancy Complications</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D011584">Psychology</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D012098">Reproduction</DescriptorName>
</MeshHeading>
<MeshHeading>
<DescriptorName MajorTopicYN="N" UI="D012106">Research</DescriptorName>
</MeshHeading>
</MeshHeadingList>
<OtherID Source="PIP">550018</OtherID>
<OtherID Source="POP">00020417</OtherID>
<OtherAbstract Type="PIP" Language="eng">
<AbstractText>Pregnancy, delivery, and neonatal records of mentally defective chil dren born in Baltimore, Maryland, between 1935 and 1952 were compared wi th records of a control group to determine the effects of complications during these 3 phases on  eventual mental disorders.  The mental defectives had a greater amount of recorded complications of pregnancy and delivery, prematurity, and abnormal neonatal experiences.  Nonmechanical pregnancy complications, i.e., bleeding or toxemia, seemed more significant in this association than the mechanical factors of delivery.  Neither duration of labor nor operative procedures performed at delivery was related with the development of mental deficiency.  The associations that did show were not as strong for nonwhites.  Various reasons for this fact are considered.  It seems possible that reproductive casualty can occur during pregnancy, delivery, and the neonatal period.  In its most serious form, it causes spontaneous abortion, stillbirth, neonatal deaths.  Lesser brain damage during these periods will cause, in the following order, cerebral palsy, epilepsy, mental retardation, and behavioral disorders.</AbstractText>
</OtherAbstract>
<KeywordList Owner="PIP">
<Keyword MajorTopicYN="N">Behavior</Keyword>
<Keyword MajorTopicYN="Y">Comparative Studies</Keyword>
<Keyword MajorTopicYN="N">Congenital Abnormalities</Keyword>
<Keyword MajorTopicYN="N">Diseases</Keyword>
<Keyword MajorTopicYN="N">Handicapped</Keyword>
<Keyword MajorTopicYN="N">Intelligence</Keyword>
<Keyword MajorTopicYN="Y">Maternal-fetal Exchange</Keyword>
<Keyword MajorTopicYN="Y">Mental Retardation</Keyword>
<Keyword MajorTopicYN="N">Personality</Keyword>
<Keyword MajorTopicYN="N">Pregnancy</Keyword>
<Keyword MajorTopicYN="Y">Pregnancy Complications</Keyword>
<Keyword MajorTopicYN="N">Psychological Factors</Keyword>
<Keyword MajorTopicYN="N">Reproduction</Keyword>
<Keyword MajorTopicYN="N">Research Methodology</Keyword>
<Keyword MajorTopicYN="N">Studies</Keyword>
</KeywordList>
</MedlineCitation>
    
//    val des = medline \ "DescriptorName"
//    println(des)
    
    val desc = medline \\ "DescriptorName"
//    println(desc)
//    for(node <- desc){
//      val alt = node \ "@MajorTopicYN"
//      println(alt.text)
//    }
    val major = desc.filter(f=>(f \ "@MajorTopicYN").text == "Y")
//    major.foreach(println)
    val text = major.map(_.text)
//    text.foreach(println)
    
    val list = List(1,2,3)
    list.combinations(2).foreach(println)
    println("abc".hashCode())
    println(0xFFL)
    
  }
}