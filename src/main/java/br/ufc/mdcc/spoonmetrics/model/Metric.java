package br.ufc.mdcc.spoonmetrics.model;

public enum Metric {

	// Chidamber and Kemerer (C&K) Metrics
	WMC("CK_WMC", "Weighted Methods per Class", ""), 
	DIT("CK_DIT", "Depth of Inheritance Tree", ""),
	NOC("CK_NOC", "Number of Children", ""), 
	CBO("CK_CBO", "Coupling Between Objects", ""),
	RFC("CK_RFC", "Response For Class", ""), 
	LCOM("CK_LCOM", "Lack of Cohesion of Methods v1", ""),

	// Other OO Metrics
	LoC("OO_LoC", "Lines of Code", ""), 
	LCOM2("OO_LCOM2", "Lack of Cohesion of Methods v2", ""),
	LCOM3("OO_LCOM3", "Lack of Cohesion of Methods v3", ""), 
	Ca("OO_Ca", "Fan in = Afferent Coupling", ""),
	Ce("OO_Ce", "Fan out = Efferent Coupling", ""),

	// Homework!
	NODA("HW_NODA", "Number of Declared Attributes", "Número de atributos declarados em uma classe."),
	NOPA("HW_NOPA", "Number of Public Attributes", "Número de atributos públicos declarados em uma classe."),
	NOPRA("HW_NOPRA", "Number of Private Attributes", "Número de atributos privados declarados em uma classe."),
	NODM("HW_NODM", "Number of Declared Methods", "Número de métodos declarados em uma classe."),
	NOPM("HW_NOPM", "Number of Public Methods", "Número de métodos públicos declarados em uma classe."),
	NOPRM("HW_NOPRM", "Number of Private Methods", "Número de métodos privados declarados em uma classe."),
	DNIF("HW_DNIF", "Depth of Nested IF", "Número da maior profundidade de IF aninhados em uma classe."),
	DNFOR("HW_DNFOR", "Depth of Nested FOR", "Número da maior profundidade de FOR aninhados em uma classe."),
	NOECB("HW_NOECB", "Number of Empty Catch Blocks", "Número de blocos catch vazios em uma classe"),
	NOSE("HW_NOSE", "Number of Signaled Exceptions", "Número de exceções sinalizadas (throws) por uma classe."),
	NORE("HW_NORE", "Number of Raised Exceptions", "Número de exceções levantadas (throw) por uma classe.");


	private final String shortName;

	private final String fullName;

	private final String description;

	Metric(String shortName, String fullName, String description) {
		this.shortName = shortName;
		this.fullName = fullName;
		this.description = description;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDescription() {
		return description;
	}
}
