<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
    
        <section class="content">
            <div id="cuerpo" class="container">
                <div class="header-container">
                     <a href="javascript:history.back()" class="btn-back">
                            Volver
                        </a>
                  
                    <h2 class="page-title">
                        Disponible en depósito: 
                        <span class="deposito-nombre">${nombreDeposito}</span>
                        <span class="lote-info"> · Lote ${idOrdenDeProduccion}</span>
                    </h2>
                    <div class="search-container">
                        <input 
                            type="text" 
                            id="searchEtiqueta" 
                            placeholder="Buscar etiqueta..."
                            class="search-input"
                        />
                    </div>
                </div>
            </div>

           <div class="stock-container" id="stockContainer">

    <c:choose>

        <c:when test="${not empty etiquetas}">
            <c:forEach var="etiqueta" items="${etiquetas}">
                <div class="stock-card ${etiqueta.tipo.toLowerCase()} etiqueta-card">
                    <div class="stock-header">
                        <h3 class="codigo-etiqueta">${etiqueta.codigo}</h3>
                        <span class="badge">${etiqueta.tipo}</span>
                    </div>
                </div>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <div class="empty-state">
                <h3>No hay etiquetas disponibles</h3>
                <p>Este depósito no tiene etiquetas para el lote seleccionado.</p>
            </div>
        </c:otherwise>

    </c:choose>

</div>
        </section>
    </div>


<style>
    /* =========================
   Variables de color
========================= */
:root {
  --primary: #1e3a8a;
  --success: #16a34a;   
  --warning: #d97706;   
  --text-main: #111827;
  --text-muted: #6b7280;
  --border-soft: #e5e7eb;
  --bg-card: #ffffff;
}

/* =========================
   Layout
========================= */
.empty-state {
  grid-column: 1 / -1; /* ocupa todo el ancho del grid */
  text-align: center;
  padding: 60px 20px;
  border: 1px dashed #d1d5db;
  border-radius: 12px;
  background: #fafafa;
}

.empty-icon {
  font-size: 36px;
  margin-bottom: 12px;
  opacity: 0.6;
}

.empty-state h3 {
  margin: 0 0 8px 0;
  font-size: 18px;
  font-weight: 500;
  color: #374151;
}

.empty-state p {
  margin: 0;
  font-size: 14px;
  color: #6b7280;
}
.stock-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
  gap: 12px;
  margin-top: 16px;
  
  max-height: 500px;      /* altura visible */
  overflow-y: auto;       /* scroll vertical */
  padding-right: 6px;     /* espacio para que no choque el scrollbar */
}
.header-container {
  margin-bottom: 25px;
}

.search-container {
  margin: 20px 0 10px 0;
  display: flex;
  justify-content: center;
}

.search-input {
  width: 100%;
  max-width: 420px;
  padding: 10px 14px;
  border-radius: 8px;
  border: 1px solid #e5e7eb;
  background: #ffffff;
  font-size: 14px;
  color: #374151;
  transition: all 0.2s ease;
  outline: none;
}

/* Placeholder más sutil */
.search-input::placeholder {
  color: #9ca3af;
  font-weight: 400;
}

/* Focus minimalista */
.search-input:focus {
  border-color: #94a3b8;
  box-shadow: 0 0 0 2px rgba(148, 163, 184, 0.15);
}

/* Hover suave */
.search-input:hover {
  border-color: #cbd5e1;
}

.page-title {
  margin: 0;
  font-size: 20px;
  font-weight: 500;
  letter-spacing: 0.3px;
}

.deposito-nombre {
  font-weight: 600;
}

.lote-info {
  font-weight: 400;
}
/* =========================
   Card base
========================= */
.stock-card {
  background: var(--bg-card);
  border-radius: 12px;
  padding: 14px;
  border: 1px solid var(--border-soft);
  border-top: 3px solid var(--border-soft);
  box-shadow: 0 4px 14px rgba(0, 0, 0, 0.05);
  transition: box-shadow 0.2s ease, transform 0.2s ease;
}

.stock-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 22px rgba(0, 0, 0, 0.08);
}

/* =========================
   Header
========================= */
.stock-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}


/* =========================
   Badge (outline)
========================= */
.badge {
  font-size: 11px;
  font-weight: 600;
  letter-spacing: 0.6px;
  padding: 4px 12px;
  border-radius: 999px;
  background: transparent;
  border: 1px solid currentColor;
  text-transform: uppercase;
}

/* =========================
   Body
========================= */
.stock-header h3 {
  margin: 0;
  font-size: 17px;
  font-weight: 700;
  letter-spacing: 0.4px;
  color: var(--text-main);
}

.stock-body p {
  margin: 3px 0;
  font-size: 14px;
  color: var(--text-muted);
}

.stock-body p strong {
  color: var(--text-main);
  font-weight: 600;
}

.contains {
  margin-top: 6px;
  font-size: 13px;
  font-weight: 500;
  color: var(--text-muted);
}

/* =========================
   Variantes por tipo
========================= */

/* Pallet */
.stock-card.pallet {
  border-top-color: var(--primary);
}

.stock-card.pallet .badge {
  color: var(--primary);
}

/* Bulto */
.stock-card.bulto {
  border-top-color: var(--success);
}

.stock-card.bulto .badge {
  color: var(--success);
}

/* Bobina */
.stock-card.bobina {
  border-top-color: var(--warning);
}

.stock-card.bobina .badge {
  color: var(--warning);
}

  
  
</style>
<script>
document.getElementById("searchEtiqueta").addEventListener("keyup", function() {
    let filtro = this.value.toLowerCase();
    let cards = document.querySelectorAll(".etiqueta-card");

    cards.forEach(function(card) {
        let codigo = card.querySelector(".codigo-etiqueta").textContent.toLowerCase();

        if (codigo.includes(filtro)) {
            card.style.display = "";
        } else {
            card.style.display = "none";
        }
    });
});
</script>
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>