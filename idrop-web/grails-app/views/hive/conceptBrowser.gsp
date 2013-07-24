<div id="conceptBrowserMain" class="container-fluid">
	<div class="row-fluid " id="conceptBrowserVocabularyToolbar">
		<div class="span2">
			<div class="btn-group pad-around">
				<a href="#myModal" role="button" class="btn btn-primary" data-toggle="modal"
					id="showSelectionListing" onclick="processShowSelectionListing()" >
					<g:message code="text.choose.vocabularies" />
				</a>

				<button type="button" class="btn btn-primary" id="backToTop"
					onclick="processVocabularySelection()">
					<g:message code="text.back.to.top" />
				</button>
			</div>	
		</div>
		
		<div class="offset1 span9">
			<div class="btn-group pad-around" data-toggle="buttons-radio">
				<g:each in="${hiveState.selectedVocabularies}"
					var="selectedVocabulary">
					<button type="button" class="btn  btn-inverse"
						id="${selectedVocabulary}"
						onclick="processVocabularySelection('${selectedVocabulary}')">
						${selectedVocabulary}
					</button>
					<button class="btn btn-inverse dropdown-toggle" data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="icon-refresh"></i>Refresh</a></li>
						<li><a href="#"><i class="icon-trash"></i>Delete</a></li>
						<li class="divider"></li>
						<li><a href="#">Make Admin</a></li>
					</ul>
				</g:each>
			</div>
		</div>
	</div>	
	<div class="row-fluid " id="conceptBrowserSearchForm">
		<div class="span5 offset1" style="margin-left: 10px" id="searchConceptForm">
			<g:form id="searchConceptForm" action="search" method="get">
				<g:textField name="searchConcept" id="searchConceptTerm" 
					value="${params.searchedConcept}" onkeydown="if (event.keyCode == 13) processSearchHiveConcept()"/>
				<button type="button" class="btn" id="searchConcept" value="update"
					onclick="processSearchHiveConcept()"><i class="icon-search"></i>
					<g:message code="text.search" />
				</button>
			</g:form>
		</div>
	</div>
	<g:if test="${selectedTerms}">
		<div class="row-fluid">
			<div class="span10 offset1">
				<button type="button" class="btn btn-link" style="display:block" data-toggle="collapse" data-target="#appliedTermsCollapseArea"><g:message code="text.show.hide.applied.terms" /></button>
			</div>
		</div>
	</g:if>
	<div class="row-fluid " id="appliedTermsCollapseArea" class="collapse">
			<div class="span10 offset1 well">
				<div class="well">
					<table class="table table-striped">
					<h3><g:message code="text.applied.terms" /></h3>
						<thead>
							<tr style="color:#0088CC">
							<th><g:message code="text.term" /></th>
							<th><g:message code="text.vocabulary" /></th>
							<th><g:message code="text.comment" /></th>
							</tr>
						</thead>
							<g:each in="${selectedTerms}" var="term">
								<tr id="${term.preferredLabel}" onmouseover="changeTextColor('${term.preferredLabel}')" onmouseout="changeTextColorBack('${term.preferredLabel}')">
									<td>
										<span onclick="processSelectOfTermAsCurrent('${term.termURI}')">${term.preferredLabel}</span>
									</td>
									<td>
										${term.vocabularyName}
									</td>
									<td>
										${term.comment}
									</td>
								</tr>
							</g:each>
						</tr>
						 </tbody>
					</table>
				</div>
			</div>

		</div>
	
	<div class="container-fluid">
		<div class="row-fluid ">
			<div class="span12" id="searchConceptResults"></div>
		</div>
	</div>
	<div id="conceptBrowserPivotContainer">
		<g:render template="/hive/conceptBrowserPivotView" />
	</div>
</div>

				<div id="myModal" class="modal hide fade" role="dialog" tabindex="2000" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
						<h3 id="myModalLabel">Vocabulary List</h3>
					</div>
					<div class="modal-body">
						<form name="hiveVocabularyForm" id="hiveVocabularyForm">
						<table cellspacing="0" cellpadding="0" border="0" id="hiveVocabTable"
							class="table table-striped table-hover" style="width:60%; margin-left:auto; margin-right: auto">
								<g:hiddenField name="absPath" id="absPath" value="${absPath	}"/>
								<thead>
									<tr>
										<th></th>
										<th><g:message code="text.vocabulary" /></th>
									</tr>
								</thead>
								<tbody>
									<g:each in="${vocabs}" var="vocab">
										<tr id="${vocab.vocabularyName}">
											<td><g:checkBox name="selectedVocab" value="${vocab.vocabularyName}"
													checked="${vocab.selected}" /></td>
											<td>
												${vocab.vocabularyName}
											</td>
										</tr>
									</g:each>
								</tbody>
								<tfoot>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</tfoot>
							</table>
						</form>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
						<button class="btn btn-primary" data-dismiss="modal">Save changes</button>
					</div>
				</div>

<div id="conceptBrowserDialog"></div>

<script type="text/javascript">
	/**
	 * page level action signals to select a new vocabulary
	 */
	function processVocabularySelection(vocabName) {
		if (vocabName == null || vocabName == "") {
			vocabName = null;
		}

		var absPath = $("#infoAbsPath").val();

		if (absPath == null) {
			setErrorMessage(jQuery.i18n.prop('msg_path_missing'));
			return false;
		}

		resetVocabulary(vocabName, absPath);
	}

	/**
	 * page level action to show the vocabulary selection form for reselection
	 */
	function processShowSelectionListing() {
		<%--alert("show vocabulary selection listing to rechoose");--%>
		$('#myModal').modal({
			backdrop: false,
			show: false
			});

		<%--$('#myModal').dialog({
			height:140,
			modal:true

			});

		$('#myModal').css({
			display:'block'
			});	--%>	
	}

	/**
	 * Pivot the concept browser to the new term
	 */
	function processSelectOfTermAsCurrent(termUri) {
		if (termUri == null || termUri == "") {
			setErrorMessage(jQuery.i18n.prop('msg_no_form_data'));
			return false;
		}

		var absPath = $("#infoAbsPath").val();

		if (absPath == null) {
			setErrorMessage(jQuery.i18n.prop('msg_path_missing'));
			return false;
		}

		browseToUri(termUri, absPath);
	}

	/**
	 * handle pressing the view in skos button
	 */
	function processViewInSKOS(vocabulary, termUri) {
		
		if (termUri == null || termUri == "") {
			setErrorMessage(jQuery.i18n.prop('msg_no_form_data'));
			return false;
		}
		if (vocabulary == null || vocabulary == "") {
			setErrorMessage(jQuery.i18n.prop('msg_no_form_data'));
			return false;
		}
		<%--alert("to be implemented, view as SKOS term:" + termUri
				+ " from vocabulary:" + vocabulary );
		
		('#btnViewInSKOS').clickover({
			global_close:false;
			title:vocabulary,
			content:'Term URI: ' + termUri
		});--%>
		
		<%--$('#btnViewInSKOS').popover({
			placement:'top',
			title:vocabulary,
			content:skosCode,
			trigger:'manual'
		}).click(function(evt){
			evt.stopPropagation();
			$(this).popover('show');
		});

		$('div').click(function(){
			$('#btnViewInSKOS').popover('hide');
		});--%>

		$('#skosCodeModal').dialog({
			height:550,
			width:630,
			modal:true

			});

		$('#skosCodeModal').css({
			display:'block'
			});	

		var element = document.getElementById('skosCodeArea');
		element.style.display = '';
		element.style.fontSize = "small";
		element.style.height = "510px";
		element.style.width = "580px";
	}

	/**
	 * handle pressing the apply term button
	 */
	function processApplyHiveTerm(vocabulary, termUri) {
		if (termUri == null || termUri == "") {
			setErrorMessage(jQuery.i18n.prop('msg_no_form_data'));
			return false;
		}
		if (vocabulary == null || vocabulary == "") {
			setErrorMessage(jQuery.i18n.prop('msg_no_form_data'));
			return false;
		}

		var absPath = $("#infoAbsPath").val();

		if (absPath == null) {
			setErrorMessage(jQuery.i18n.prop('msg_path_missing'));
			return false;
		}

		applyHiveTerm(absPath, vocabulary, termUri);

	}

	function processSearchHiveConcept() {
		var searchedConcept = $("#searchConceptTerm").val();
		if (!searchedConcept) {
			setMessage("No search term entered");
			return false;
		}
		searchConcept(searchedConcept);
	}


	function changeTextColor(id) {
		var element = document.getElementById(id);
		element.style.color = "#FF8000";
	}

	function changeTextColorBack(id) {
		var element = document.getElementById(id);
		element.style.color = "#333333";
	}
</script>