document.getElementById('filtroForm').addEventListener('submit', function () {
    const bodegaId = document.getElementById('bodegaId').value;
    if (!bodegaId) return;
    fetch(`/warehouse/v1/inventario/bodega/${bodegaId}`)
        .then(resp => {
            if (!resp.ok) throw new Error('No se pudo obtener inventarios');
            return resp.json();
        })
        .then(data => {
            const inventarios = data.inventarios || [];
            const tbody = document.getElementById('tablaInventarios');
            tbody.innerHTML = '';
            if (inventarios.length === 0) {
                tbody.innerHTML = '<tr><td colspan="3">No hay inventarios</td></tr>';
            } else {
                inventarios.forEach(inv => {
                    tbody.innerHTML += `<tr>
                    <td>${inv.producto && inv.producto.nombre ? inv.producto.nombre : 'N/A'}</td>
                    <td>${inv.bodega && inv.bodega.nombre ? inv.bodega.nombre : 'N/A'}</td>
                    <td>${inv.cantidad}</td>
                </tr>`;
                });
            }
        })
        .catch(() => {
            document.getElementById('tablaInventarios').innerHTML = '<tr><td colspan="3">Error al obtener inventarios</td></tr>';
        });
});
