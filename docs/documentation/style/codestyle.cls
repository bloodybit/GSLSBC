\definecolor{codegreen}{rgb}{0,0.6,0}
\definecolor{codegray}{rgb}{0.5,0.5,0.5}
\definecolor{codepurple}{rgb}{0.58,0,0.82}
\definecolor{backcolor}{rgb}{0.95,0.95,0.95}
\definecolor{violet}{cmyk}{0.45,0.97,0.27,0.21}
\definecolor{lstblue}{cmyk}{1,0.80,0,0}
\definecolor{lstgreen}{cmyk}{0.71,0.21,0.65,0.22}
\definecolor{bluegrey}{cmyk}{0.56,0.24,0.11,0.05}
\definecolor{javadoc}{cmyk}{0.88,0.59,0,0}
\definecolor{lstgrey}{cmyk}{0.55,0.44,0.42,0.32}

\usepackage{listings}

\lstdefinestyle{JSONStyle}{
    backgroundcolor=\color{backcolor},
    commentstyle=\color{codegreen},
    keywordstyle=\color{black},
    numberstyle=\tiny\color{codegray},
    stringstyle=\color{codegreen},
    basicstyle=\ttfamily\scriptsize,
    breakatwhitespace=false,
    breaklines=true,
    captionpos=b,
    keepspaces=true,
    numbersep=5pt,
    showspaces=false,
    showstringspaces=false,
    showtabs=false,
    tabsize=2
}

\lstdefinestyle{RFCStyle}{
	backgroundcolor=\color{backcolor},
	commentstyle=\color{codegreen},
	keywordstyle=\color{black},
	numberstyle=\tiny\color{codegray},
	stringstyle=\color{codegreen},
	basicstyle=\ttfamily\small,
	breakatwhitespace=false,
	breaklines=true,
	captionpos=b,
	keepspaces=true,
	numbersep=5pt,
	showspaces=false,
	showstringspaces=false,
	showtabs=false,
	tabsize=2
}

%\lstset{
%	numbers=left,
%	numberstyle=\tiny,
%	numbersep=5pt,
%	breaklines=true,
%	stepnumber=1,
%	tabsize=2,
%	basicstyle=\ttfamily\small,
%	frame=none,
%	numberfirstline=true,
%	firstnumber=1,
%	keywordstyle=\color{violet}\bfseries,
%	ndkeywordstyle=\color{bluegrey}\bfseries,
%	identifierstyle=\color{black},
%	commentstyle=\color{lstgreen}\ttfamily,
%	stringstyle=\color{lstblue}\ttfamily,
%	showstringspaces=false
%}

\lstdefinelanguage{SQL}{
	keywords={},
	keywordstyle=\color{bluegrey}\bfseries,
	morekeywords=[2]{CREATE,TABLE,IF,NOT,EXISTS,NULL,SET,DEFAULT,PRIMARY,KEY,COLLATE,CHARACTER,AUTO_INCREMENT,ENGINE,CHARSET},
	keywordstyle={[2]\color{violet}\bfseries},
	otherkeywords={int,varchar,double,text,tinyint},
	sensitive=false,
	morecomment=[l][\color{lstgreen}]{//},
	morecomment=[s][\color{lstgreen}]{/*}{*/},
	morecomment=[s][\color{javadoc}]{/**}{*/},
	morestring=[b]',
	morestring=[b]"
}
\lstdefinelanguage{PHP}{
	keywords={},
	keywordstyle=\color{bluegrey}\bfseries,
	morekeywords=[2]{static,function,if,return,pow,sin,cos,asin,min,sqrt,int},
	keywordstyle={[2]\color{violet}\bfseries},
	otherkeywords={@param, @returns, @author, @type, @link, @see},
	sensitive=false,
	morecomment=[l][\color{lstgreen}]{//},
	morecomment=[s][\color{lstgreen}]{/*}{*/},
	morecomment=[s][\color{javadoc}]{/**}{*/},
	morestring=[b]',
	morestring=[b]"
}
\lstdefinelanguage{JavaScript}{
	keywords={},
	keywordstyle=\color{bluegrey}\bfseries,
	morekeywords=[2]{attributes, class, classend, do, empty, endif, endwhile, fail, function, functionend, if, implements, in, inherit, inout, not, of, operations, out, return, set, then, types, while, use},
	keywordstyle={[2]\color{violet}\bfseries},
	otherkeywords={@param, @returns, @author, @type, @link, @see},
	sensitive=false,
	morecomment=[l][\color{lstgreen}]{//},
	morecomment=[s][\color{lstgreen}]{/*}{*/},
	morecomment=[s][\color{javadoc}]{/**}{*/},
	morestring=[b]',
	morestring=[b]"
}
\lstdefinelanguage{Java}{
	keywords={},
	keywordstyle=\color{bluegrey}\bfseries,
	morekeywords=[2]{abstract,boolean,break,byte,case,catch,char,class,
		const,continue,default,do,double,else,extends,false,final,
		finally,float,for,goto,if,implements,import,instanceof,int,
		interface,label,long,native,new,null,package,private,protected,
		public,return,short,static,super,switch,synchronized,this,throw,
		throws,transient,true,try,void,volatile,while},
	keywordstyle={[2]\color{violet}\bfseries},
	morekeywords=[3]{@SuppressWarnings, @Capability, @Override},
	keywordstyle={[3]\color{lstgrey}},
	otherkeywords={@param, @return, @returns, @author, @link, @see},
	sensitive,
	morecomment=[l]//,
	morecomment=[s]{/*}{*/},
	morecomment=[s][\color{javadoc}]{/**}{*/},
	morestring=[b]",
	morestring=[b]',
}[keywords,comments,strings]

% some listings styles from Gregor Aisch
% http://vis4.net/blog/2009/09/noch-mehr-sprach-definitionen-fuer-latex-listings/

\lstdefinelanguage{HTML5}
{
	morekeywords={a, abbr, address, area, article, aside, audio, b, base, bb, bdo, blockquote,  body, br, button, canvas, caption, cite, code, col, colgroup, command, datagrid, datalist, dd, del, details, dialog, dfn, div, dl, dt, em, embed, eventsource, fieldset, figure, footer,  form,  h1, h2,  h3,  h4, h5,  h6,  head,  header,  hr, html,  i, iframe,  img,  input,  ins, kbd,  label,  legend,  li,  link,  mark,  map,  menu,  meta,  meter,  nav,  noscript,  object,  ol,  optgroup,  option,  output,  p,  param,  pre,  progress,  q,  ruby,  rp,  rt,  samp,  script,  section,  select,  small,  source,  span,  strong,  style,  sub,  sup,  table,  tbody,  td,  textarea,  tfoot,  th,  thead,  time,  title,  tr,  ul,  var,  video},
	sensitive=false,
	morecomment=[s]{<!--}{-->},
	morestring=[b]",
	morestring=[d]'
}

\lstdefinelanguage{CSS}{
	morekeywords={azimuth,  background-attachment,  background-color,  background-image,  background-position,  background-repeat,  background,  border-collapse,  border-color,  border-spacing,  border-style,  border-top, border-right, border-bottom, border-left,  border-top-color, border-right-color, border-bottom-color, border-left-color,  border-top-style, border-right-style, border-bottom-style, border-left-style,  border-top-width, border-right-width, border-bottom-width, border-left-width,  border-width,  border,  bottom,  caption-side,  clear,  clip,  color,  content,  counter-increment,  counter-reset,  cue-after,  cue-before,  cue,  cursor,  direction,  display,  elevation,  empty-cells,  float,  font-family,  font-size,  font-style,  font-variant,  font-weight,  font,  height,  left,  letter-spacing,  line-height,  list-style-image,  list-style-position,  list-style-type,  list-style,  margin-right, margin-left,  margin-top, margin-bottom,  margin,  max-height,  max-width,  min-height,  min-width,  orphans,  outline-color,  outline-style,  outline-width,  outline,  overflow,  padding-top, padding-right, padding-bottom, padding-left,  padding,  page-break-after,  page-break-before,  page-break-inside,  pause-after,  pause-before,  pause,  pitch-range,  pitch,  play-during,  position,  quotes,  richness,  right,  speak-header,  speak-numeral,  speak-punctuation,  speak,  speech-rate,  stress,  table-layout,  text-align,  text-decoration,  text-indent,  text-transform,  top,  unicode-bidi,  vertical-align,  visibility,  voice-family,  volume,  white-space,  widows,  width,  word-spacing,  z-index},
	sensitive=false,
	morecomment=[s]{/*}{*/},
	morestring=[b]",
	morestring=[d]'
}