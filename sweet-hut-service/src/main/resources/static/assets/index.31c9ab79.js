import{d as _,u as p,r as l,o as h,c as m,a as e,w as n,b as t}from"./index.67aad9ee.js";const w={class:"cookery-book-list"},C=["src"],f=t("button",{class:"add-cookery-book-button"},"\u65B0\u589E\u83DC\u8C31",-1),g=["onClick"],b=t("div",{class:"svg-wrapper-1"},[t("div",{class:"svg-wrapper"},[t("svg",{xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 24 24",width:"24",height:"24"},[t("path",{fill:"none",d:"M0 0h24v24H0z"}),t("path",{fill:"currentColor",d:"M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"})])])],-1),D=t("span",null,"\u66F4\u591A",-1),v=[b,D],E=_({__name:"index",setup(B){const s=p(),c=[{name:"\u7EA2\u70E7\u9CAB\u9C7C",type:"\u5DDD\u83DC",first_date:"2022-09-09",creator:"\u54E5\u54E5",image:new URL(""+new URL("fish.41b0f20c.png",import.meta.url).href,self.location).href},{name:"\u7D2B\u83DC\u86CB\u6C64",type:"\u5DDD\u83DC",first_date:"2022-09-09",creator:"\u5B9D\u5B9D",image:new URL(""+new URL("zicaidantang.5e4b5ccc.png",import.meta.url).href,self.location).href}],i=r=>{s.push("/cookery-book/detail")};return(r,k)=>{const o=l("el-table-column"),u=l("el-table"),d=l("el-card");return h(),m("div",w,[e(d,{class:"box-card"},{default:n(()=>[e(u,{data:c,"header-cell-style":{background:"#eee",color:"#606266",fontSize:"18px"},"cell-style":{fontSize:"16px"},border:"",stripe:"",style:{width:"100%"}},{default:n(()=>[e(o,{type:"index",align:"center","min-width":"5%"}),e(o,{prop:"name",label:"\u83DC\u8C31\u540D\u79F0",align:"center","min-width":"15%"}),e(o,{prop:"type",label:"\u83DC\u7CFB",align:"center","min-width":"10%"}),e(o,{prop:"first_date",label:"\u5B8C\u6210\u65F6\u95F4",align:"center","min-width":"15%"}),e(o,{prop:"creator",label:"\u5B8C\u6210\u4EBA",align:"center","min-width":"15%"}),e(o,{label:"\u6210\u54C1\u5C55\u793A",align:"center","min-width":"20%"},{default:n(a=>[t("img",{src:a.row.image,width:"90",height:"90"},null,8,C)]),_:1}),e(o,{align:"center","min-width":"20%"},{header:n(()=>[f]),default:n(a=>[t("button",{class:"view-details-button",onClick:y=>i(a.row)},v,8,g)]),_:1})]),_:1})]),_:1})])}}});export{E as default};
