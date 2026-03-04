<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    * { box-sizing: border-box; }

    .mv-wrapper {
        font-family: 'DM Sans', sans-serif;
        padding: 24px;
        background: #f4f6f9;
        min-height: 100vh;
    }

    .mv-topbar {
        display: flex;
        align-items: center;
        gap: 16px;
        margin-bottom: 24px;
    }
    .mv-back {
        display: flex;
        align-items: center;
        gap: 6px;
        color: #6b7280;
        font-size: 13px;
        text-decoration: none;
        font-weight: 500;
        transition: color 0.2s;
    }
    .mv-back:hover { color: #1a1a2e; }
    .mv-back svg { width: 14px; height: 14px; }
    .mv-title {
        font-size: 20px;
        font-weight: 600;
        color: #1a1a2e;
        margin: 0;
        letter-spacing: -0.3px;
    }

    .mv-search-bar {
        background: #fff;
        border: 1px solid #e5e7eb;
        border-radius: 12px;
        padding: 14px 18px;
        margin-bottom: 24px;
        display: flex;
        gap: 10px;
        align-items: center;
    }
    .mv-search-bar input {
        flex: 1;
        border: 1px solid #e5e7eb;
        border-radius: 8px;
        padding: 9px 14px;
        font-size: 14px;
        font-family: 'DM Sans', sans-serif;
        color: #1a1a2e;
        max-width: 280px;
        outline: none;
        transition: border-color 0.2s;
    }
    .mv-search-bar input:focus { border-color: #3FBFBF; }
    .mv-search-bar button {
        background: #1a1a2e;
        color: #fff;
        border: none;
        border-radius: 8px;
        padding: 9px 20px;
        font-size: 14px;
        font-family: 'DM Sans', sans-serif;
        font-weight: 500;
        cursor: pointer;
        transition: background 0.2s;
    }
    .mv-search-bar button:hover { background: #2d2d4e; }
    .mv-search-msg { font-size: 13px; color: #9ca3af; }

    .mv-result-label {
        font-size: 13px;
        color: #6b7280;
        font-weight: 500;
        margin-bottom: 16px;
    }
    .mv-result-label strong { color: #1a1a2e; }

    .deposito-section { margin-bottom: 25px; }

    .deposito-header {
        background: #fff;
        color: #1a1a2e;
        padding: 12px 20px;
        border-radius: 10px 10px 0 0;
        border-top: 4px solid #3FBFBF;
        display: flex;
        justify-content: space-between;
        align-items: center;
        gap: 10px;
        flex-wrap: wrap;
        box-shadow: 0 1px 0 #e5e7eb;
    }
    .deposito-header-left {
        display: flex;
        align-items: center;
        gap: 8px;
        flex-wrap: wrap;
    }
    .deposito-header h4 { margin: 0; font-size: 16px; font-weight: 600; color: #1a1a2e; }
    .deposito-chip {
        background: #f0fdfa;
        border: 1px solid #99e6e6;
        border-radius: 20px;
        padding: 4px 12px;
        font-size: 12px;
        font-weight: 600;
        white-space: nowrap;
        color: #0f766e;
    }
    .deposito-badge {
        background: #3FBFBF;
        color: #fff;
        padding: 4px 12px;
        border-radius: 20px;
        font-size: 13px;
        font-weight: 600;
        white-space: nowrap;
    }

    .deposito-search {
        padding: 10px 15px;
        border-bottom: 1px solid #eee;
        background: #fafafa;
    }
    .deposito-search input {
        width: 100%;
        padding: 7px 12px;
        border: 1px solid #ddd;
        border-radius: 6px;
        font-size: 13px;
        outline: none;
        font-family: 'DM Sans', sans-serif;
    }
    .deposito-search input:focus { border-color: #3FBFBF; }

    .deposito-body {
        background: #fff;
        border-radius: 0 0 10px 10px;
        box-shadow: 0 2px 6px rgba(0,0,0,0.08);
        max-height: 350px;
        overflow-y: auto;
    }
    .deposito-body::-webkit-scrollbar { width: 4px; }
    .deposito-body::-webkit-scrollbar-track { background: transparent; }
    .deposito-body::-webkit-scrollbar-thumb { background: #3FBFBF; border-radius: 4px; }

    .movimiento-row {
        display: flex;
        align-items: center;
        padding: 12px 20px;
        border-bottom: 1px solid #f0f0f0;
        gap: 15px;
    }
    .movimiento-row:last-child { border-bottom: none; }
    .movimiento-row:hover { background: #f9f9f9; }

    .tipo-badge {
        width: 42px;
        height: 42px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-weight: bold;
        font-size: 11px;
        flex-shrink: 0;
        color: white;
    }
    .tipo-bobina { background-color: #d97706; }
    .tipo-bulto  { background-color: #16a34a; }
    .tipo-pallet { background-color: #1e3a8a; }

    .movimiento-info { flex: 1; }
    .movimiento-codigo { font-weight: bold; font-size: 15px; color: #333; }
    .movimiento-fecha { font-size: 12px; color: #999; margin-top: 2px; }

    .dep-empty {
        padding: 15px 20px;
        font-size: 13px;
        color: #999;
        display: none;
    }

    #resultadoBusqueda { display: none; }
    
    .bultos-container {
    margin-top: 6px;
    display: flex;
    flex-wrap: wrap;
    gap: 6px;
}

.bulto-chip {
    background: #f3f4f6;
    border: 1px solid #e5e7eb;
    border-radius: 20px;
    padding: 3px 10px;
    font-size: 12px;
    font-weight: 500;
    color: #374151;
    transition: all 0.2s ease;
}

.bulto-chip:hover {
    background: #e0f2f1;
    border-color: #3FBFBF;
    color: #0f766e;
}
</style>

<div class="content-wrapper">
    <section class="content">
    <div class="mv-wrapper">

        <div class="mv-topbar">
            <a href="javascript:history.back()" class="mv-back">
                <svg fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/></svg>
                Volver
            </a>
            <h2 class="mv-title">Movimientos de Baja</h2>
        </div>

        <%-- Buscador remito --%>
        <div class="mv-search-bar">
            <input type="text" id="inputNroRemito" placeholder="Nro de remito..."
                   value="${idRemito}" />
            <button onclick="buscarRemito()">Buscar</button>
            <span id="mensajeBusqueda" class="mv-search-msg"></span>
        </div>

        <%-- Resultado --%>
        <div id="resultadoBusqueda">
            <p class="mv-result-label">Remito <strong id="nroRemitoEncontrado"></strong></p>
            <div id="contenedorDepositos"></div>
        </div>

    </div>
    </section>
</div>

<script>
    $(document).ready(function() {
        var nro = '${idRemito}';
        if(nro !== '') {
            mostrarDatos();
        }
    });

    function mostrarDatos() {
        var html = '';

        <c:forEach var="detalle" items="${remitoDetalles}">
            var movimientos = 0;
            var sectionHtml = '';

            <c:set var="nombreDep" value="${nombreDepositoPorId[detalle.idDeposito]}"/>
            <c:if test="${empty nombreDep}">
                <c:set var="nombreDep" value="Deposito ${detalle.idDeposito}"/>
            </c:if>

            <c:set var="denominacion" value="${denominacionPorDetalle[detalle.id]}"/>
            <c:if test="${empty denominacion}">
                <c:set var="denominacion" value=""/>
            </c:if>

            <c:set var="nroOrden" value="${ordenPorDetalle[detalle.id]}"/>
            <c:if test="${empty nroOrden}">
                <c:set var="nroOrden" value=""/>
            </c:if>

            <c:set var="egresos" value="${egresosPorDetalle[detalle.id]}"/>
            <c:forEach var="egreso" items="${egresos}">
                <c:set var="listaBultos" value="${bultosPorPallet[egreso.idPallet]}" />
                movimientos++;
                var tipo = '';
                var codigo = '';

                <c:choose>
                    <c:when test="${egreso.idBobina != null}">
                        tipo = 'bobina';
                        codigo = 'B${egreso.idBobina}';
                    </c:when>
                    <c:when test="${egreso.idBulto != null}">
                        tipo = 'bulto';
                        codigo = '${codigoBultoPorId[egreso.idBulto]}';
                    </c:when>
                    <c:when test="${egreso.idPallet != null}">
                        tipo = 'pallet';
                        codigo = 'P${egreso.idPallet}';
                    </c:when>
                </c:choose>

                <c:set var="fechaFormateada">
                    <fmt:formatDate value="${egreso.fechaBaja}" pattern="dd/MM/yyyy"/>
                </c:set>

                sectionHtml += '<div class="movimiento-row">';
                sectionHtml += '  <div class="tipo-badge tipo-' + tipo + '">' + tipoBadgeLabel(tipo) + '</div>';
                sectionHtml += '  <div class="movimiento-info">';
                sectionHtml += '    <div class="movimiento-codigo">' + codigo + '</div>';
                sectionHtml += '    <div class="movimiento-fecha">${fechaFormateada}</div>';
                sectionHtml += '  </div>';
                <c:if test="${egreso.idPallet != null && not empty listaBultos}">
                    sectionHtml += '<div class="bultos-container">';
                    <c:forEach var="bulto" items="${listaBultos}">
                        sectionHtml += '<span class="bulto-chip">${bulto.codigo}</span>';
                    </c:forEach>
                    sectionHtml += '</div>';
                </c:if>
                sectionHtml += '</div>';
            </c:forEach>

            html += '<div class="deposito-section">';
            html += '  <div class="deposito-header">';
            html += '    <div class="deposito-header-left">';
            html += '      <h4>${nombreDep}</h4>';
            <c:if test="${not empty denominacion}">
            html += '      <span class="deposito-chip">Articulo: ${denominacion}</span>';
            </c:if>
            <c:if test="${not empty nroOrden}">
            html += '      <span class="deposito-chip">Orden de Producción: ${nroOrden}</span>';
            </c:if>
            html += '    </div>';
            html += '    <span class="deposito-badge">' + movimientos + ' movimientos</span>';
            html += '  </div>';
            html += '  <div class="deposito-search">';
            html += '    <input type="text" placeholder="Buscar por codigo..." onkeyup="filtrarMovimientos(this)" />';
            html += '  </div>';
            html += '  <div class="deposito-body">';
            html += sectionHtml;
            html += '    <div class="dep-empty">Sin resultados.</div>';
            html += '  </div>';
            html += '</div>';
        </c:forEach>

        $('#contenedorDepositos').html(html);
        $('#nroRemitoEncontrado').text('${idRemito}');
        $('#resultadoBusqueda').show();
    }

    function filtrarMovimientos(input) {
        var valor = input.value.toLowerCase().trim();
        var section = $(input).closest('.deposito-section');
        var rows = section.find('.movimiento-row');
        var visibles = 0;

        rows.each(function() {
            var codigo = $(this).find('.movimiento-codigo').text().toLowerCase();
            if(valor === '' || codigo.indexOf(valor) !== -1) {
                $(this).show();
                visibles++;
            } else {
                $(this).hide();
            }
        });

        section.find('.dep-empty').toggle(visibles === 0 && valor !== '');
    }

    function buscarRemito() {
        var nro = $('#inputNroRemito').val().trim();
        if(nro === '') {
            $('#mensajeBusqueda').text('Ingrese un numero de remito.');
            return;
        }
        window.location.href = '/thyssenplastic/remitoDetalle/verMovimientosRemito/' + nro;
    }

    function tipoBadgeLabel(tipo) {
        if(tipo === 'bobina') return 'B';
        if(tipo === 'bulto')  return 'R';
        if(tipo === 'pallet') return 'P';
        return tipo;
    }
</script>
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>