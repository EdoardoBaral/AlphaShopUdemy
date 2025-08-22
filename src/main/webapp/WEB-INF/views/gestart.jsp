<!doctype html>
<html lang="en">
	<%@ include file="common/head.jspf" %>

	<body>
		<%@ include file="common/navbar.jspf" %>
		<section class="content-main" style="max-width: 760px">
			<div class="content-header">
				<h2 class="content-title">${title}</h2>
				<div>
					<a href="#" id="btnAbort" class="btn btn-outline-danger">
						<spring:message code="form.gestart.btnAbort.label"/>
					</a>
				</div>
			</div>
			<div class="card mb-4">
				<div class="card-body">
					<form:form method="POST" modelAttribute="datiart">
						<form:errors path="*" cssClass="alert alert-danger" element="div"/>
						<div class="row gx-2">
							<!-- Codice Articolo -->
							<fieldset class="col-sm-6 mb-3">
								<form:label for="codArt" path="codArt" cssClass="form-label">
									<spring:message code="form.gestart.codArt.label"/>
								</form:label>
								<form:input id="codart" path="codArt" type="text" cssClass="form-control" placeholder="Codice Articolo" disabled="${isModifica}"/>
								<form:errors path="codArt" cssClass="text-danger"/>
							</fieldset>

							<!-- Barcode -->
							<div class="col-sm-6 mb-3">
								<label for="ean" class="form-label">Barcode:</label>
								<select name="ean" class="form-select"></select>
							</div>
						</div>

						<!--Descrizione -->
						<fieldset class="mb-4">
							<form:label for="descrizione" path="descrizione" cssClass="form-label">
								<spring:message code="form.gestart.descrizione.label"/>
							</form:label>
							<form:input id="descrizione" path="descrizione" type="text" cssClass="form-control" placeholder="Descrizione Articolo"/>
							<form:errors path="descrizione" cssClass="text-danger"/>
						</fieldset>

						<div class="row gx-2">
							<!--Unità di misura-->
							<fieldset class="col-sm-6 mb-3">
								<form:label for="um" path="um" cssClass="form-label">
									<spring:message code="form.gestart.um.label"/>
								</form:label>
								<form:select path="um" cssClass="form-select">
									<form:option value="PZ" label="Pezzi"/>
									<form:option value="LT" label="Litri"/>
									<form:option value="KG" label="Kilogrammi"/>
								</form:select>
							</fieldset>

							<!-- Iva -->
							<fieldset class="col-sm-6 mb-3">
								<form:label for="um" path="iva" cssClass="form-label">
									<spring:message code="form.gestart.iva.label"/>
								</form:label>
								<form:select path="iva" cssClass="form-select">
									<form:option value="" label="--Seleziona Iva--"/>
									<form:options items="${iva}" itemValue="idIva" itemLabel="descrizione"/>
								</form:select>
								<form:errors path="iva" cssClass="text-danger"/>
							</fieldset>
						</div>

						<!-- row.// -->
						<div class="mb-4">
							<div class="row gx-2">
								<!-- Pezzi per Cartone -->
								<fieldset class="col-3">
									<form:label for="pzCart" path="pzCart" cssClass="form-label">
										<spring:message code="form.gestart.pzCart.label"/>
									</form:label>
									<form:input id="pzCart" path="pzCart" type="number" cssClass="form-control"/>
								</fieldset>

								<!-- Peso Netto -->
								<fieldset class="col-3">
									<form:label for="pesoNetto" path="pesoNetto" cssClass="form-label">
										<spring:message code="form.gestart.pesoNetto.label"/>
									</form:label>
									<form:input id="pesoNetto" path="pesoNetto" type="text" cssClass="form-control"/>
								</fieldset>

								<!--Prezzo -->
								<fieldset class="col-4">
									<label class="form-label">
										<spring:message code="form.gestart.prezzo.label"/>
									</label>
									<input placeholder="Prezzo" type="text" class="form-control">
								</fieldset>
								<div class="col-2">
									<label class="form-label">
										<spring:message code="form.gestart.valuta.label"/>
									</label>
									<select class="form-select">
										<option> EUR </option>
										<option> USD </option>
										<option> GBP </option>
									</select>
								</div>
							</div>
						</div>
						<div class="row gx-2">
							<!-- Categoria -->
							<fieldset class="col-sm-6 mb-3">
								<form:label for="pesoNetto" path="pesoNetto" cssClass="form-label">
									<spring:message code="form.gestart.famAssort.label"/>
								</form:label>
								<form:select path="famAssort" cssClass="form-select">
									<form:options items="${famAssort}" itemValue="id" itemLabel="descrizione"/>
								</form:select>
							</fieldset>

							<!-- Stato Articolo  -->
							<fieldset class="col-sm-6 mb-3">
								<form:label for="pesoNetto" path="idStatoArt" cssClass="form-label">
									<spring:message code="form.gestart.idStatoArt.label"/>
								</form:label>
								<form:select path="idStatoArt" cssClass="form-select">
									<form:option value="1" label="Attivo"/>
									<form:option value="2" label="Sospeso"/>
									<form:option value="3" label="Eliminato"/>
								</form:select>
							</fieldset>
						</div>

						<!-- row.// -->
						<!-- Immagine -->
						<div class="mb-4">
							<label class="form-label">
								<spring:message code="form.gestart.immagine.label"/>
							</label>
							<input class="form-control" type="file" name="file"/>
						</div>
						<br>
							<input type="submit"
								   id="btnAdd"
								   class="btn btn-primary form-buttons"
								   value="<spring:message code=" form.gestart.btnAdd.label"/>" />
					</form:form>
				</div>
			</div>
		</section>

		<%@ include file="common/foot.jspf" %>
	</body>
</html>