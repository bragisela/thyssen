<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

<div class="content-wrapper">
    <section class="content">
        <div class="container">

           <div class="mv-topbar">
                
                <h2 class="mv-title">Ingreso a deposito - Mantas</h2>
            </div>

            <c:if test="${param.exito == 'true'}">
                <div id="bannerExito" style="margin-bottom:16px; background:#f0fff4; border:2px solid #38a169; border-radius:10px; padding:20px 24px; display:flex; align-items:center; gap:16px">
                    <div style="background:#38a169; border-radius:50%; width:44px; height:44px; display:flex; align-items:center; justify-content:center; flex-shrink:0">
                        <i class="fa fa-check" style="color:#fff; font-size:20px"></i>
                    </div>
                    <div style="flex:1">
                        <div style="font-size:16px; font-weight:700; color:#276749">Ingreso registrado correctamente</div>
                        <div style="font-size:13px; color:#4a7c59; margin-top:2px">Las bobinas fueron ingresadas al depósito.</div>
                    </div>
                    <button onclick="document.getElementById('bannerExito').style.display='none'" style="background:none; border:none; cursor:pointer; color:#38a169; font-size:22px; line-height:1; padding:0">&times;</button>
                </div>
            </c:if>

            <!-- PASO 1: ESTADO -->
            <div class="box box-default">
                <div class="box-header with-border">
                    <h3 class="box-title"><span class="step-badge">1</span> Selecciona el estado de las bobinas</h3>
                </div>
                <div class="box-body">
                   <div class="estado-grid">
                        <div class="estado-btn" data-estado="ok" 
                             data-deposito-id="${depositoPorEstado.ok}" 
                             data-deposito-nombre="${nombreDepositoPorEstado.ok}" 
                             onclick="seleccionarEstado(this)">
                            <span class="dot dot-ok"></span>OK
                        </div>
                        <div class="estado-btn" data-estado="observado" 
                             data-deposito-id="${depositoPorEstado.observado}" 
                             data-deposito-nombre="${nombreDepositoPorEstado.observado}" 
                             onclick="seleccionarEstado(this)">
                            <span class="dot dot-observado"></span>Observado
                        </div>
                        <div class="estado-btn" data-estado="sinmesurar" 
                             data-deposito-id="${depositoPorEstado.sinmesurar}" 
                             data-deposito-nombre="${nombreDepositoPorEstado.sinmesurar}" 
                             onclick="seleccionarEstado(this)">
                            <span class="dot dot-sinmesurar"></span>Sin Mesurar
                        </div>
                    </div>

                    <div id="depositoInfo" class="deposito-info" style="display:none">
                        Deposito destino: <strong id="depositoNombre"></strong>
                    </div>
                </div>
            </div>

            <!-- PASO 2: ORDEN -->
            <div class="box box-default">
                <div class="box-header with-border">
                    <h3 class="box-title"><span class="step-badge">2</span> Selecciona la orden de produccion</h3>
                </div>
                <div class="box-body">
                    <select class="form-control" id="selectOrden" onchange="onOrdenChange()" style="max-width:400px">
                        <option value="">-- Selecciona una orden --</option>
                        <c:forEach var="orden" items="${ordenes}">
                            <option value="${orden.id}">#${orden.id} &mdash; ${orden.estado} &mdash; ${orden.fecha}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- PASO 3: BOBINAS -->
            <div class="box box-default">
                <div class="box-header with-border">
                    <h3 class="box-title"><span class="step-badge">3</span> Bobinas disponibles</h3>
                </div>
                <div class="box-body">

                    <div class="alert alert-warning" id="alertaEstado" style="display:none">
                        Solo podes seleccionar bobinas del mismo estado. Deselecciona las actuales para cambiar.
                    </div>

                    <div id="placeholderCard" class="text-center" style="padding:30px; color:#aaa">
                        Selecciona una orden para ver las bobinas disponibles
                    </div>

                    <div id="loadingState" class="text-center" style="padding:30px; display:none">
                        <i class="fa fa-spinner fa-spin fa-2x"></i>
                        <p>Cargando bobinas...</p>
                    </div>

                    <div id="emptyState" class="text-center" style="padding:30px; color:#aaa; display:none">
                        No se encontraron bobinas para esta orden
                    </div>

                    <div id="tableBobinasContainer" style="display:none">
                        <div style="margin-bottom:12px; display:flex; align-items:center; gap:12px">
                            <input type="text" class="form-control" id="searchBobina" placeholder="Buscar por codigo..." oninput="filtrarBobinas()" style="max-width:280px; display:inline-block"/>
                            <span style="font-size:12px; color:#888" id="contadorBobinas"></span>
                        </div>

                        <div style="max-height:420px; overflow-y:auto; border:1px solid #ddd; border-radius:4px">
                            <table class="table table-bordered table-hover table-condensed" style="margin-bottom:0">
                                <thead style="position:sticky; top:0; z-index:1">
                                    <tr style="background:#f4f4f4">
                                        <th style="width:40px">
                                            <input type="checkbox" id="checkAll" onchange="toggleAll(this)" title="Seleccionar todas"/>
                                        </th>
                                        <th>Codigo</th>
                                        <th>Estado</th>
                                        <th>En deposito</th>
                                    </tr>
                                </thead>
                                <tbody id="tbodyBobinas"></tbody>
                            </table>
                        </div>
                    </div>

                    <!-- ACCIONES inline -->
                    <div id="accionesContainer" style="display:none; margin-top:14px; align-items:center; justify-content:space-between">
                        <span style="font-size:14px; color:#555">
                            <strong id="countSeleccionadas" style="font-size:18px; color:#3FBFBF">0</strong> bobinas seleccionadas
                        </span>
                        <button class="btn btn-primary" id="btnIngresar" disabled onclick="confirmarIngreso()">
                            Ingresar al deposito
                        </button>
                    </div>

                </div>
            </div>

        </div>
    </section>
</div>

<!-- FORM oculto para POST -->
<form id="formIngreso" method="POST" action="/thyssenplastic/ingresarDeposito/mantas/procesar" style="display:none">
    <input type="hidden" name="idDeposito" id="hiddenIdDeposito"/>
    <div id="hiddenBobinas"></div>
</form>

<style>
    .step-badge {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        background: #3FBFBF;
        color: #fff;
        border-radius: 50%;
        width: 22px;
        height: 22px;
        font-size: 11px;
        font-weight: 700;
        margin-right: 6px;
        vertical-align: middle;
    }
    .estado-grid {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
    }
    .mv-topbar {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 20px;
    margin-top: 10px;
}
.mv-title {
    font-size: 20px;
    font-weight: 600;
    color: #1a1a2e;
    margin: 0;
    letter-spacing: -0.3px;
}
    .estado-btn {
        border: 2px solid #ddd;
        border-radius: 8px;
        padding: 10px 18px;
        cursor: pointer;
        font-size: 13px;
        font-weight: 600;
        color: #555;
        background: #fff;
        transition: all 0.15s;
        user-select: none;
    }
    .estado-btn:hover  { border-color: #3FBFBF; background: #f0fdfa; }
    .estado-btn.active { border-color: #3FBFBF; background: #e6fffa; color: #2c7a7b; }
    .dot {
        width: 10px; height: 10px;
        border-radius: 50%;
        display: inline-block;
        margin-right: 5px;
        vertical-align: middle;
    }
    .dot-ok         { background: #38a169; }
    .dot-observado  { background: #d69e2e; }
    .dot-rechazado  { background: #e53e3e; }
    .dot-sinmesurar { background: #718096; }
    .deposito-info {
        margin-top: 14px;
        padding: 10px 16px;
        background: #f0fdfa;
        border: 1px solid #b2f5ea;
        border-radius: 6px;
        font-size: 14px;
        color: #2c7a7b;
    }
    .badge-estado {
        display: inline-block;
        border-radius: 10px;
        padding: 2px 10px;
        font-size: 11px;
        font-weight: 600;
    }
    .badge-ok         { background: #c6f6d5; color: #276749; }
    .badge-observado  { background: #fefcbf; color: #744210; }
    .badge-rechazado  { background: #fed7d7; color: #822727; }
    .badge-sinmesurar { background: #e2e8f0; color: #4a5568; }
    .badge-deposito   { background: #bee3f8; color: #2b6cb0; }
    .check-bobina {
        accent-color: #3FBFBF;
        width: 15px; height: 15px;
        cursor: pointer;
    }
    .check-bobina:disabled { opacity: 0.35; cursor: not-allowed; }
    tr.fila-bloqueada td { opacity: 0.4; }
</style>

<script>
    var estadoSeleccionado = null;
    var depositoIdSeleccionado = null;
    var depositoNombreSeleccionado = null;
    var todasLasBobinas = [];
    var estadoBloqueado = null;

    function seleccionarEstado(btn) {
        document.querySelectorAll('.estado-btn').forEach(function(b) { b.classList.remove('active'); });
        btn.classList.add('active');
        estadoSeleccionado = btn.dataset.estado;
        depositoIdSeleccionado = btn.dataset.depositoId;
        depositoNombreSeleccionado = btn.dataset.depositoNombre;
        document.getElementById('depositoNombre').textContent = depositoNombreSeleccionado;
        document.getElementById('depositoInfo').style.display = 'block';

        if (todasLasBobinas.length > 0) {
            var prevChecked = [];
            document.querySelectorAll('#tbodyBobinas .check-bobina:checked').forEach(function(c) {
                prevChecked.push(c.value);
            });

            estadoBloqueado = null;
            renderBobinas(todasLasBobinas);

            prevChecked.forEach(function(val) {
                var m = document.querySelector('#tbodyBobinas .check-bobina[value="' + val + '"]:not(:disabled)');
                if (m) m.checked = true;
            });

            actualizarContador();
            actualizarCheckAll();
        }
    }

    function onOrdenChange() {
        var idOrden = document.getElementById('selectOrden').value;
        if (!idOrden) {
            document.getElementById('placeholderCard').style.display = 'block';
            document.getElementById('tableBobinasContainer').style.display = 'none';
            document.getElementById('accionesContainer').style.display = 'none';
            document.getElementById('emptyState').style.display = 'none';
            todasLasBobinas = [];
            estadoBloqueado = null;
            actualizarContador();
            return;
        }
        cargarBobinas(idOrden);
    }

    function cargarBobinas(idOrden) {
        document.getElementById('placeholderCard').style.display = 'none';
        document.getElementById('tableBobinasContainer').style.display = 'none';
        document.getElementById('accionesContainer').style.display = 'none';
        document.getElementById('emptyState').style.display = 'none';
        document.getElementById('loadingState').style.display = 'block';
        estadoBloqueado = null;
        actualizarContador();

        fetch('/thyssenplastic/ingresarDeposito/mantas/bobinas/' + idOrden)
            .then(function(r) { return r.json(); })
            .then(function(data) {
                document.getElementById('loadingState').style.display = 'none';
                todasLasBobinas = data;
                if (!data || data.length === 0) {
                    document.getElementById('emptyState').style.display = 'block';
                } else {
                    document.getElementById('tableBobinasContainer').style.display = 'block';
                    document.getElementById('accionesContainer').style.display = 'flex';
                    renderBobinas(data);
                }
            })
            .catch(function() {
                document.getElementById('loadingState').style.display = 'none';
                document.getElementById('emptyState').style.display = 'block';
            });
    }

    function renderBobinas(bobinas) {
        var tbody = document.getElementById('tbodyBobinas');
        tbody.innerHTML = '';
        var searchVal = document.getElementById('searchBobina').value.toLowerCase();
        var visible = 0;

        bobinas.forEach(function(b) {
            if (searchVal && b.codigo.toLowerCase().indexOf(searchVal) === -1) return;
            visible++;

            var tieneDeposito = b.tieneDeposito === 'true';
            var mismoEstado = (estadoBloqueado === null || estadoBloqueado === b.estado);
            var esEstadoFiltrado = estadoSeleccionado && b.estado !== estadoSeleccionado;
            var deshabilitada = tieneDeposito || (!mismoEstado && estadoBloqueado !== null) || esEstadoFiltrado;

            var tr = document.createElement('tr');
            if (deshabilitada) tr.classList.add('fila-bloqueada');

            var tdCheck = document.createElement('td');
            var check = document.createElement('input');
            check.type = 'checkbox';
            check.className = 'check-bobina';
            check.value = b.id;
            check.dataset.estado = b.estado;
            check.disabled = deshabilitada;
            check.onchange = function() { onCheckChange(this); };
            tdCheck.appendChild(check);

            var tdCodigo = document.createElement('td');
            tdCodigo.textContent = b.codigo;
            tdCodigo.style.fontWeight = '600';

            var tdEstado = document.createElement('td');
            tdEstado.innerHTML = estadoBadge(b.estado);

            var tdDep = document.createElement('td');
            tdDep.innerHTML = tieneDeposito
                ? '<span class="badge-estado badge-deposito">Ya ingresada</span>'
                : '<span style="color:#ccc">-</span>';

            tr.appendChild(tdCheck);
            tr.appendChild(tdCodigo);
            tr.appendChild(tdEstado);
            tr.appendChild(tdDep);
            tbody.appendChild(tr);
        });

        document.getElementById('contadorBobinas').textContent = visible + ' bobina' + (visible !== 1 ? 's' : '');
        actualizarCheckAll();
    }

    function estadoBadge(estado) {
        var map = {
            'ok':         '<span class="badge-estado badge-ok">OK</span>',
            'observado':  '<span class="badge-estado badge-observado">Observado</span>',
            'rechazado':  '<span class="badge-estado badge-rechazado">Rechazado</span>',
            'sinmesurar': '<span class="badge-estado badge-sinmesurar">Sin Mesurar</span>'
        };
        return map[estado] || '<span style="color:#aaa">-</span>';
    }

    function onCheckChange(checkbox) {
        var checks = document.querySelectorAll('#tbodyBobinas .check-bobina:checked');
        if (checks.length === 0) {
            estadoBloqueado = null;
            renderBobinas(todasLasBobinas);
        } else {
            estadoBloqueado = checkbox.dataset.estado;
            var prevChecked = [];
            checks.forEach(function(c) { prevChecked.push(c.value); });
            renderBobinas(todasLasBobinas);
            prevChecked.forEach(function(val) {
                var m = document.querySelector('#tbodyBobinas .check-bobina[value="' + val + '"]');
                if (m) m.checked = true;
            });
            var cur = document.querySelector('#tbodyBobinas .check-bobina[value="' + checkbox.value + '"]');
            if (cur) cur.checked = checkbox.checked;
        }
        var alguno = document.querySelectorAll('#tbodyBobinas .check-bobina:checked');
        if (alguno.length > 0 && estadoSeleccionado && estadoBloqueado !== estadoSeleccionado) {
            document.getElementById('alertaEstado').style.display = 'block';
        } else {
            document.getElementById('alertaEstado').style.display = 'none';
        }
        actualizarContador();
        actualizarCheckAll();
    }

    function toggleAll(masterCheck) {
        var checks = document.querySelectorAll('#tbodyBobinas .check-bobina:not(:disabled)');
        var prevChecked = [];
        checks.forEach(function(c) {
            c.checked = masterCheck.checked;
            if (masterCheck.checked) prevChecked.push(c.value);
        });
        var primero = document.querySelector('#tbodyBobinas .check-bobina:checked');
        estadoBloqueado = primero ? primero.dataset.estado : null;
        renderBobinas(todasLasBobinas);
        prevChecked.forEach(function(val) {
            var m = document.querySelector('#tbodyBobinas .check-bobina[value="' + val + '"]');
            if (m) m.checked = true;
        });
        actualizarContador();
        actualizarCheckAll();
    }

    function actualizarCheckAll() {
        var total    = document.querySelectorAll('#tbodyBobinas .check-bobina:not(:disabled)').length;
        var marcados = document.querySelectorAll('#tbodyBobinas .check-bobina:checked').length;
        var ca = document.getElementById('checkAll');
        ca.checked = total > 0 && marcados === total;
        ca.indeterminate = marcados > 0 && marcados < total;
    }

    function filtrarBobinas() {
        if (todasLasBobinas.length > 0) renderBobinas(todasLasBobinas);
    }

    function actualizarContador() {
        var count = document.querySelectorAll('#tbodyBobinas .check-bobina:checked').length;
        document.getElementById('countSeleccionadas').textContent = count;
        document.getElementById('btnIngresar').disabled = (count === 0 || !depositoIdSeleccionado);
    }

    function confirmarIngreso() {
        var checks = document.querySelectorAll('#tbodyBobinas .check-bobina:checked');
        if (checks.length === 0) return;
        if (!depositoIdSeleccionado) {
            alert('Selecciona un estado primero para determinar el deposito destino.');
            return;
        }
        if (!confirm('Confirmar el ingreso de ' + checks.length + ' bobina' + (checks.length !== 1 ? 's' : '') + ' al deposito ' + depositoNombreSeleccionado + '?')) return;

        var btn = document.getElementById('btnIngresar');
        btn.disabled = true;
        btn.innerHTML = '<i class="fa fa-spinner fa-spin"></i> Guardando...';

        document.getElementById('hiddenIdDeposito').value = depositoIdSeleccionado;
        var container = document.getElementById('hiddenBobinas');
        container.innerHTML = '';
        checks.forEach(function(c) {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'idsBobinas';
            input.value = c.value;
            container.appendChild(input);
        });
        document.getElementById('formIngreso').submit();
    }
    
    // Reset del boton si el browser restaura la pagina desde el historial
    window.addEventListener('pageshow', function() {
        var btn = document.getElementById('btnIngresar');
        btn.innerHTML = 'Ingresar al deposito';
        // actualizarContador re-evalua si debe estar habilitado o no
        actualizarContador();
    });
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>
